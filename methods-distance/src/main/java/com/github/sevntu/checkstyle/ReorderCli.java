////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2019 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.github.sevntu.checkstyle;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.github.sevntu.checkstyle.common.MethodCallDependencyCheckInvoker;
import com.github.sevntu.checkstyle.domain.Dependencies;
import com.github.sevntu.checkstyle.dsm.DependencyInfoMatrixSerializer;
import com.github.sevntu.checkstyle.module.DependencyInformationConsumer;
import com.github.sevntu.checkstyle.ordering.MethodOrder;
import com.github.sevntu.checkstyle.reordering.TopologicalMethodReorderer;
import com.github.sevntu.checkstyle.utils.FileUtils;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;

/**
 * Application entry point that accepts file path and generates
 * DSM for initial methods ordering of class and DSM for method
 * ordering generated by topological sorting algorithm.
 * @author Zuy Alexey
 */
public final class ReorderCli {

    private ReorderCli() {
        // no code
    }

    public static void main(String... args) throws CheckstyleException {

        final Map<String, String> attributes = Collections.singletonMap("screenLinesCount", "50");

        final DependencyInformationSerializer serializer = new DependencyInformationSerializer();

        final MethodCallDependencyCheckInvoker runner =
            new MethodCallDependencyCheckInvoker(attributes, serializer);

        final List<File> files = Collections.singletonList(new File(args[0]));
        runner.invoke(files);
    }

    private static final class DependencyInformationSerializer implements
        DependencyInformationConsumer {

        private Configuration configuration;

        private DependencyInformationSerializer() {
            // no code
        }

        @Override
        public void setConfiguration(Configuration config) {
            this.configuration = config;
        }

        @Override
        public void accept(String filePath, Dependencies dependencies) {
            final String baseName = new File(filePath).getName();
            final String source = FileUtils.getFileContents(filePath);
            final MethodOrder initialMethodOrder = new MethodOrder(dependencies);
            final MethodOrder topologicalMethodOrder = new TopologicalMethodReorderer()
                .reorder(initialMethodOrder);
            DependencyInfoMatrixSerializer.writeToFile(
                source, initialMethodOrder, configuration, baseName + ".initial.html");
            DependencyInfoMatrixSerializer.writeToFile(
                source, topologicalMethodOrder, configuration, baseName + ".topological.html");
        }
    }
}
