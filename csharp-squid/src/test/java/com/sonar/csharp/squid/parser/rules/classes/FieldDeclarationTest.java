/*
 * Copyright (C) 2009-2012 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.squid.parser.rules.classes;

import static com.sonar.sslr.test.parser.ParserMatchers.*;
import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

import com.sonar.csharp.squid.CSharpConfiguration;
import com.sonar.csharp.squid.api.CSharpGrammar;
import com.sonar.csharp.squid.parser.CSharpParser;
import com.sonar.sslr.impl.Parser;

public class FieldDeclarationTest {

  private final Parser<CSharpGrammar> p = CSharpParser.create(new CSharpConfiguration(Charset.forName("UTF-8")));
  private final CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.fieldDeclaration);
    g.attributes.mock();
    g.type.mock();
    g.variableDeclarator.mock();
  }

  @Test
  public void testOk() {
    assertThat(p, parse("type variableDeclarator;"));
    assertThat(p, parse("type variableDeclarator, variableDeclarator;"));
    assertThat(p, parse("attributes type variableDeclarator;"));
    assertThat(p, parse("attributes new type variableDeclarator;"));
    assertThat(p, parse("public protected internal private static readonly volatile type variableDeclarator;"));
  }

}