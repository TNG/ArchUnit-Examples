package com.tngtech.archunit.exampletest;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.example.cycles.complexcycles.slice1.SliceOneCallingConstructorInSliceTwoAndMethodInSliceThree;
import com.tngtech.archunit.example.cycles.complexcycles.slice3.ClassCallingConstructorInSliceFive;
import com.tngtech.archunit.lang.Priority;
import com.tngtech.archunit.library.dependencies.SliceAssignment;
import com.tngtech.archunit.library.dependencies.SliceIdentifier;
import org.junit.Test;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class CyclicDependencyRulesTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.tngtech.archunit.example.cycles");

    @Test
    public void no_cycles_by_method_calls_between_slices() {
        slices().matching("..(simplecycle).(*)..").namingSlices("$2 of $1")
                .should().beFreeOfCycles()
                .check(classes);
    }

    @Test
    public void no_cycles_by_constructor_calls_between_slices() {
        slices().matching("..(constructorcycle).(*)..").namingSlices("$2 of $1")
                .should().beFreeOfCycles()
                .check(classes);
    }

    @Test
    public void no_cycles_by_inheritance_between_slices() {
        slices().matching("..(inheritancecycle).(*)..").namingSlices("$2 of $1")
                .should().beFreeOfCycles()
                .check(classes);
    }

    @Test
    public void no_cycles_by_field_access_between_slices() {
        slices().matching("..(fieldaccesscycle).(*)..").namingSlices("$2 of $1")
                .should().beFreeOfCycles()
                .check(classes);
    }

    @Test
    public void no_cycles_by_member_dependencies_between_slices() {
        slices().matching("..(membercycle).(*)..").namingSlices("$2 of $1")
                .should().beFreeOfCycles()
                .check(classes);
    }

    @Test
    public void no_cycles_in_simple_scenario() {
        slices().matching("..simplescenario.(*)..").namingSlices("$1")
                .should().beFreeOfCycles()
                .check(classes);
    }

    @Test
    public void no_cycles_in_complex_scenario() {
        slices().matching("..(complexcycles).(*)..").namingSlices("$2 of $1")
                .should().beFreeOfCycles()
                .check(classes);
    }

    @Test
    public void no_cycles_in_complex_scenario_with_custom_ignore() {
        slices().matching("..(complexcycles).(*)..").namingSlices("$2 of $1")
                .as("Slices of complex scenario ignoring some violations")
                .should().beFreeOfCycles()
                .ignoreDependency(SliceOneCallingConstructorInSliceTwoAndMethodInSliceThree.class, ClassCallingConstructorInSliceFive.class)
                .ignoreDependency(resideInAPackage("..slice4.."), alwaysTrue())
                .check(classes);
    }

    @Test
    public void no_cycles_in_freely_customized_slices() {
        slices().assignedFrom(inComplexSliceOneOrTwo(), Priority.HIGH)
                .namingSlices("$1[$2]")
                .should().beFreeOfCycles()
                .check(classes);
    }

    private SliceAssignment inComplexSliceOneOrTwo() {
        return new SliceAssignment() {
            @Override
            public String getDescription() {
                return "complex slice one or two";
            }

            @Override
            public SliceIdentifier getIdentifierOf(JavaClass javaClass) {
                if (javaClass.getPackageName().contains("complexcycles.slice1")) {
                    return SliceIdentifier.of("Complex-Cycle", "One");
                }
                if (javaClass.getPackageName().contains("complexcycles.slice2")) {
                    return SliceIdentifier.of("Complex-Cycle", "Two");
                }
                return SliceIdentifier.ignore();
            }
        };
    }
}
