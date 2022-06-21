package com.example

import spock.lang.Specification

class DataDrivenTests extends Specification {

    def "matrix"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        1 | 2 | 2
        2 | 1 | 2
        3 | 2 | 3
    }

    def "list"() {
        expect:
        Math.log(a) <= a

        where:
        a << [1, 2, 3]
    }
}
