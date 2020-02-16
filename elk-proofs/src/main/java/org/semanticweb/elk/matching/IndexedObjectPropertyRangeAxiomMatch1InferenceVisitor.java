package org.semanticweb.elk.matching;

/*
 * #%L
 * ELK Proofs Package
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2016 Department of Computer Science, University of Oxford
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyRangeAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiomInference;

class IndexedObjectPropertyRangeAxiomMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedObjectPropertyRangeAxiomMatch1>
		implements IndexedObjectPropertyRangeAxiomInference.Visitor<Void> {

	IndexedObjectPropertyRangeAxiomMatch1InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedObjectPropertyRangeAxiomMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ElkObjectPropertyRangeAxiomConversion inference) {
		factory.getElkObjectPropertyRangeAxiomConversionMatch1(inference,
				child);
		return null;
	}

}
