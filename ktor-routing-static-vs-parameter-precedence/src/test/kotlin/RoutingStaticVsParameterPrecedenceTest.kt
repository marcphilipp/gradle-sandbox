import io.ktor.client.request.basicAuth
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.resources.Resource
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.basic
import io.ktor.server.resources.Resources
import io.ktor.server.resources.get
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Minimal reproducer for a Ktor `Resources` routing regression observed after upgrading
 * from 3.5.2 to 3.6.0.
 *
 * Given two sibling resources under the same parent:
 *   - a static child resource:        /collection/summary
 *   - a parameterized child resource: /collection/{id}
 *
 * registered under two *separate* `authenticate { ... }` blocks (mirroring a real
 * application that groups routes by required authentication), a request to the static
 * path (`/collection/summary`) is expected to match the static resource. In 3.6.0 it
 * instead matches the parameterized resource, with the literal path segment ("summary")
 * bound to the `id` parameter.
 */
@Resource("/collection")
class Collection {
    @Resource("/summary")
    class Summary(val parent: Collection = Collection())

    @Resource("/{id}")
    class ById(val parent: Collection = Collection(), val id: String)
}

class RoutingStaticVsParameterPrecedenceTest {

    @Test
    fun `static sibling resource in an earlier authenticate block should take precedence`() = testApplication {
        application {
            install(Authentication) {
                basic("group-a") { validate { UserIdPrincipal("user") } }
                basic("group-b") { validate { UserIdPrincipal("user") } }
            }
            install(Resources)
            routing {
                authenticate("group-a") {
                    get<Collection.Summary> { call.respondText("summary") }
                }
                authenticate("group-b") {
                    get<Collection.ById> { call.respondText("id=${it.id}") }
                }
            }
        }

        val response = client.get("/collection/summary") { basicAuth("user", "pass") }

        assertEquals("summary", response.bodyAsText())
    }
}
