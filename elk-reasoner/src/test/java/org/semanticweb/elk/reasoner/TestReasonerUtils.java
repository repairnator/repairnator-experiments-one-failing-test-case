/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2012 Department of Computer Science, University of Oxford
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
package org.semanticweb.elk.reasoner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.elk.loading.AxiomLoader;
import org.semanticweb.elk.loading.Owl2StreamLoader;
import org.semanticweb.elk.loading.TestAxiomLoader;
import org.semanticweb.elk.loading.TestAxiomLoaderFactory;
import org.semanticweb.elk.loading.TestChangesLoader;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.iris.ElkPrefix;
import org.semanticweb.elk.owl.parsing.Owl2ParseException;
import org.semanticweb.elk.owl.parsing.Owl2Parser;
import org.semanticweb.elk.owl.parsing.Owl2ParserAxiomProcessor;
import org.semanticweb.elk.owl.parsing.javacc.Owl2FunctionalStyleParserFactory;
import org.semanticweb.elk.reasoner.config.ReasonerConfiguration;
import org.semanticweb.elk.reasoner.stages.PostProcessingStageExecutor;
import org.semanticweb.elk.reasoner.stages.ReasonerStageExecutor;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 */
public class TestReasonerUtils {

	final static ReasonerStageExecutor DEFAULT_STAGE_EXECUTOR = new PostProcessingStageExecutor();

	public static Reasoner createTestReasoner(
			final AxiomLoader.Factory axiomLoaderFactory,
			final ReasonerInterrupter interrupter,
			final ReasonerConfiguration config) {
		return new ReasonerFactory().createReasoner(axiomLoaderFactory,
				interrupter, DEFAULT_STAGE_EXECUTOR, config);
	}

	/**
	 * Created a reasoner that fails on interrupt.
	 * 
	 * @param axiomLoaderFactory
	 * @param config
	 * @return
	 */
	public static Reasoner createTestReasoner(
			final AxiomLoader.Factory axiomLoaderFactory,
			final ReasonerConfiguration config) {
		return createTestReasoner(axiomLoaderFactory,
				FailingReasonerInterrupter.INSTANCE, config);
	}

	/**
	 * Created a reasoner that fails on interrupt.
	 * 
	 * @param axiomLoader
	 * @param config
	 * @return
	 */
	public static Reasoner createTestReasoner(final TestAxiomLoader axiomLoader,
			final ReasonerConfiguration config) {
		return createTestReasoner(new TestAxiomLoaderFactory(axiomLoader),
				config);
	}

	/**
	 * Created a reasoner that fails on interrupt and uses default config.
	 * 
	 * @param axiomLoader
	 * @return
	 */
	public static Reasoner createTestReasoner(
			final TestAxiomLoader axiomLoader) {
		return createTestReasoner(axiomLoader,
				ReasonerConfiguration.getConfiguration());
	}

	/**
	 * Created a reasoner that fails on interrupt. Closes the stream.
	 * 
	 * @param stream
	 * @param config
	 * @return
	 */
	public static Reasoner createTestReasoner(final InputStream stream,
			final ReasonerConfiguration config) {
		final AxiomLoader.Factory axiomLoaderFactory = new Owl2StreamLoader.Factory(
				new Owl2FunctionalStyleParserFactory(), stream);
		return createTestReasoner(axiomLoaderFactory, config);
	}

	/**
	 * Created a reasoner that fails on interrupt and uses default config.
	 * Closes the stream.
	 * 
	 * @param stream
	 * @return
	 */
	public static Reasoner createTestReasoner(final InputStream stream) {
		return createTestReasoner(stream,
				ReasonerConfiguration.getConfiguration());
	}

	/**
	 * Created a reasoner that fails on interrupt and uses specified number of
	 * workers.
	 * 
	 * @param axiomLoaderFactory
	 * @param maxWorkers
	 * @return
	 */
	public static Reasoner createTestReasoner(
			final AxiomLoader.Factory axiomLoaderFactory,
			final int maxWorkers) {
		ReasonerConfiguration config = ReasonerConfiguration.getConfiguration();

		config.setParameter(ReasonerConfiguration.NUM_OF_WORKING_THREADS,
				String.valueOf(maxWorkers));

		return createTestReasoner(axiomLoaderFactory, config);
	}

	/**
	 * Created a reasoner that fails on interrupt and uses specified number of
	 * workers.
	 * 
	 * @param axiomLoader
	 * @param maxWorkers
	 * @return
	 */
	public static Reasoner createTestReasoner(final TestAxiomLoader axiomLoader,
			final int maxWorkers) {
		return createTestReasoner(new TestAxiomLoaderFactory(axiomLoader),
				maxWorkers);
	}

	/**
	 * Created a reasoner that fails on interrupt and uses specified number of
	 * workers. Closes the stream.
	 * 
	 * @param stream
	 * @param maxWorkers
	 * @return
	 */
	public static Reasoner createTestReasoner(final InputStream stream,
			final int maxWorkers) {
		final AxiomLoader.Factory axiomLoaderFactory = new Owl2StreamLoader.Factory(
				new Owl2FunctionalStyleParserFactory(), stream);
		return createTestReasoner(axiomLoaderFactory, maxWorkers);
	}

	public static Reasoner createTestReasoner(final InputStream stream,
			final ReasonerInterrupter interrupter,
			final ReasonerConfiguration config) {
		final AxiomLoader.Factory axiomLoaderFactory = new Owl2StreamLoader.Factory(
				new Owl2FunctionalStyleParserFactory(), stream);
		return createTestReasoner(axiomLoaderFactory, interrupter, config);
	}

	/**
	 * Created a reasoner that uses default config.
	 * 
	 * @param stream
	 * @param interrupter
	 * @return
	 */
	public static Reasoner createTestReasoner(final InputStream stream,
			final ReasonerInterrupter interrupter) {
		return createTestReasoner(stream, interrupter,
				ReasonerConfiguration.getConfiguration());
	}

	public static Reasoner createTestReasoner(
			final ReasonerInterrupter interrupter,
			final ReasonerConfiguration config) {
		return new ReasonerFactory().createReasoner(interrupter,
				DEFAULT_STAGE_EXECUTOR, config);
	}

	public static Reasoner loadAndClassify(Set<? extends ElkAxiom> ontology)
			throws Exception {

		TestChangesLoader initialLoader = new TestChangesLoader();

		Reasoner reasoner = TestReasonerUtils.createTestReasoner(initialLoader);

		for (ElkAxiom axiom : ontology) {
			initialLoader.add(axiom);
		}

		try {
			reasoner.getTaxonomy();
		} catch (ElkInconsistentOntologyException e) {
			// shit happens
		}

		return reasoner;
	}

	public static Set<ElkAxiom> loadAxioms(InputStream stream)
			throws IOException, Owl2ParseException {
		return loadAxioms(new InputStreamReader(stream));
	}

	public static Set<? extends ElkAxiom> loadAxioms(String resource)
			throws Exception {
		return loadAxioms(TestReasonerUtils.class.getClassLoader()
				.getResourceAsStream(resource));
	}

	public static Set<? extends ElkAxiom> loadAxioms(File file)
			throws Exception {
		return loadAxioms(new FileInputStream(file));
	}

	public static Set<ElkAxiom> loadAxioms(Reader reader)
			throws IOException, Owl2ParseException {
		try {
			Owl2Parser parser = new Owl2FunctionalStyleParserFactory()
					.getParser(reader);
			final Set<ElkAxiom> axioms = new HashSet<ElkAxiom>();

			parser.accept(new Owl2ParserAxiomProcessor() {

				@Override
				public void visit(ElkPrefix elkPrefix)
						throws Owl2ParseException {
					// ignored
				}

				@Override
				public void visit(ElkAxiom elkAxiom) throws Owl2ParseException {
					axioms.add(elkAxiom);
				}

				@Override
				public void finish() throws Owl2ParseException {
					// everything is processed immediately
				}
			});

			return axioms;
		} finally {
			reader.close();
		}
	}
}
