/*
 * This Spock specification was generated by the Gradle 'init' task.
 */
package org.example

import spock.lang.Specification

class LibraryTest extends Specification {

    def 'regular test'() {
        expect:
        false
    }

    def 'nested suite'() {
        expect:
        false

        where:
        param << params()
    }

    static def params() {
        throw new RuntimeException('boom')
    }
}