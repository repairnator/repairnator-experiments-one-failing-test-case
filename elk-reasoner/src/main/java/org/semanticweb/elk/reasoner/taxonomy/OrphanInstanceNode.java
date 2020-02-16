/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2013 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collections;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceNode;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;

/**
 * An {@link OrphanNode} with one member type node
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            the type of objects in this node
 * @param <I>
 *            the type of instances of this node
 */
public class OrphanInstanceNode<T extends ElkEntity, I extends ElkEntity>
		extends OrphanNode<I> implements InstanceNode<T, I> {

	final OrphanTypeNode<T, I> typeNode;

	private final ComparatorKeyProvider<? super I> instanceKeyProvider_;

	public OrphanInstanceNode(final Iterable<? extends I> members,
			final int size, final I canonical,
			ComparatorKeyProvider<? super I> instanceKeyProvider,
			final OrphanTypeNode<T, I> typeNode) {
		super(members, size, canonical, instanceKeyProvider);
		this.typeNode = typeNode;
		this.instanceKeyProvider_ = instanceKeyProvider;
	}

	@Override
	public Set<? extends OrphanTypeNode<T, I>> getDirectTypeNodes() {
		return Collections.singleton(typeNode);
	}

	@Override
	public Set<? extends OrphanTypeNode<T, I>> getAllTypeNodes() {
		return Collections.singleton(typeNode);
	}

	@Override
	public Taxonomy<T> getTaxonomy() {
		return typeNode.getTaxonomy();
	}

	@Override
	public ComparatorKeyProvider<? super I> getKeyProvider() {
		return instanceKeyProvider_;
	}

}
