package com.tngtech.archunit.exampletest.junit5;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;

@AnalyzeClasses(packages = "com.tngtech.archunit.example.layers")
class RuleSetsTest {
    @ArchTest
    private final ArchTests CODING_RULES = ArchTests.in(CodingRulesTest.class);

    @ArchTest
    private final ArchTests NAMING_CONVENTION_RULES = ArchTests.in(NamingConventionTest.class);
}
