/*
 * Copyright (c) 2017 Bosch Software Innovations GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/index.php
 *
 * Contributors:
 *    Bosch Software Innovations GmbH - initial contribution
 */
package org.eclipse.ditto.model.enforcers.testbench.scenarios.scenario4;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.ditto.model.policies.PoliciesResourceType;
import org.eclipse.ditto.model.policies.SubjectId;
import org.eclipse.ditto.model.policies.SubjectIssuer;
import org.eclipse.ditto.model.enforcers.testbench.algorithms.PolicyAlgorithm;
import org.eclipse.ditto.model.enforcers.testbench.scenarios.Scenario;
import org.eclipse.ditto.model.enforcers.testbench.scenarios.ScenarioSetup;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;


@State(Scope.Benchmark)
public class Scenario4MultipleSubjects4 implements Scenario4MultipleSubjects {

    private final ScenarioSetup setup;

    public Scenario4MultipleSubjects4() {
        final String resource = "/features";
        setup = Scenario.newScenarioSetup(
                true,
                "Subject_1 has READ+WRITE granted on '/'. " + "Subject_3 has WRITE revoked on '/'. "
                        + "Subject_3 has READ+WRITE granted on '/attributes'. "
                        + "Subject_3 has READ+WRITE granted on '" + resource + "'. " +
                        "Subject_3 has READ revoked on '/features/foo1'. "
                        + "Subject_3 has WRITE revoked on '/features/foo2'. "
                        + "Subject_3 has READ+WRITE revoked on '/features/foo3'. "
                        + "Subject_3 has READ+WRITE revoked on '/attributes/nogo1'. "
                        + "Subject_3 has READ revoked on '/attributes/nogo2'. "
                        + "Subject_3 has WRITE revoked on '/attributes/nogo2'. "
                        + "Subject_3 has READ+WRITE granted on '/attributes/nogo1/go1'. "
                        + "Subject_3 has READ granted on '/attributes/nogo2/go2'. "
                        + "Subject_5 has READ granted on '/features/public/properties/location'. "
                        + "Subject_5 has WRITE granted on '/features/lamp/properties/config/on'. "
                        + "Is able to READ '/features' with hasPermissionsOnResourceOrAnySubresource()",
                getPolicy(),
                Scenario.newAuthorizationContext(SUBJECT_1, SUBJECT_3, SUBJECT_5),
                resource,
                Collections.emptySet(),
                policyAlgorithm -> { // as those subjects have some READ granted somewhere they shall be able to read "/" partially
                    final Set<String> sids = policyAlgorithm.getSubjectIdsWithPartialPermission(
                            PoliciesResourceType.thingResource(resource), "READ");
                    final Collection<String> expectedSids = new HashSet<>();
                    Collections.addAll(expectedSids,
                            createSubjectString(SUBJECT_1),
                            createSubjectString(SUBJECT_2),
                            createSubjectString(SUBJECT_3),
//                                        createSubjectString(SUBJECT_4), // only READ on "/attributes"
                            createSubjectString(SUBJECT_5)
//                                        createSubjectString(SUBJECT_6), // only READ on "/attributes"
//                                        createSubjectString(SUBJECT_7), // no READ granted
//                                        createSubjectString(SUBJECT_8) // only READ on "/attributes"
//                                        createSubjectString(SUBJECT_9) // no READ granted
                    );
                    return Objects.equals(sids.size(), expectedSids.size()) && sids.containsAll(expectedSids);
                },
                "READ");
    }

    private static String createSubjectString(final CharSequence subjectId) {
        return SubjectId.newInstance(SubjectIssuer.GOOGLE, subjectId).toString();
    }

    @Override
    public ScenarioSetup getSetup() {
        return setup;
    }

    @Override
    public Function<PolicyAlgorithm, Boolean> getApplyAlgorithmFunction() {
        // algorithm invoked with hasPermissionsOnResourceOrAnySubresource! as we would like to know if the subject can read anywhere
        // in the hierarchy below the passed path:
        return algorithm -> algorithm.hasPermissionsOnResourceOrAnySubresource(getSetup());
    }

}
