// SPDX-License-Identifier: MIT
package com.daimler.sechub.adapter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.daimler.sechub.adapter.support.JSONAdapterSupport;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;

public class JSONAdapterSupportTest {

	private JSONAdapterSupport supportToTest;
	private TraceIdProvider provider;
	private Adapter<?> adapter;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void before() throws Exception {
		provider = mock(TraceIdProvider.class);
		adapter = mock(Adapter.class);

		supportToTest = new JSONAdapterSupport(adapter, provider);
	}


	@Test
	public void buildFromMap_json_with_one_entry_returns_not_null() throws Exception {

		/* prepare */
		Map<String, Object> json = new TreeMap<>();
		json.put("key1", "value1");

		/* execute */
		String jsonAsString = supportToTest.toJSON(json);

		/* test */
		assertNotNull(jsonAsString);
	}

	@Test
	public void buildFromMap_json_with_empty_map_returns_not_null_but_curly_braces_with_empty_content() throws Exception {

		/* prepare */
		Map<String, Object> json = new TreeMap<>();

		/* execute */
		String jsonAsString = supportToTest.toJSON(json);

		/* test */
		assertNotNull(jsonAsString);
		assertEquals("{}", jsonAsString);
	}

	@Test
	public void buildFromMap_json_with_one_entry_returns_map_with_this_entry() throws Exception {

		/* prepare */
		Map<String, Object> json = new TreeMap<>();
		json.put("key1", "value1");

		/* execute */
		String jsonAsString = supportToTest.toJSON(json);

		/* test */
		assertNotNull(jsonAsString);
		StringValuePattern p = WireMock.equalToJson("{\"key1\":\"value1\"}");
		assertTrue(p.match(jsonAsString).isExactMatch());
	}


	@Test
	public void fetching_not_existing_element_throws_adapter_exception() throws Exception {
		/* prepare, test */
		expected.expect(AdapterException.class);

		/* execute */
		supportToTest.fetch("test", "{}");
	}


	@Test
	public void fetching_existing_element_throws_no_adapter_exception_and_returns_value() throws Exception {
		/* prepare, test */
		expected.expect(AdapterException.class);

		/* execute */
		assertEquals("1234", supportToTest.fetch("test", "{'test' = '1234'}").asText());
	}

	@Test
	public void fetching_existing_array_throws_no_adapter_exception_and_returns_array() throws Exception {
		/* prepare, test */
		expected.expect(AdapterException.class);

		String arrayText = "{'test' = [{'val' = 'value1'},{'val' = 'value2'}]}";

		/* execute */
		ArrayNode array = supportToTest.fetch("test",arrayText).asArray();
		assertNotNull(array);

	}

	@Test
	public void fetch_element_by_map_scan_for_element_with_key_alpha_returns_val_of_this_element() throws Exception {
		/* prepare, test */
		expected.expect(AdapterException.class);

		String arrayText = "{'test' = [{'key'='alpha', 'val' = 'value1'},{'key'='beta', 'val' = 'value2'}]}";

		/* execute */
		String textFound = supportToTest.fetch("test",arrayText).fetchArrayElementHaving("val", Collections.singletonMap("key","alpha")).asText();
		assertEquals("value1",textFound);

	}

	@Test
	public void fetch_element_by_map_scan_for_element_with_key_beta_returns_val_of_this_element() throws Exception {
		/* prepare, test */
		expected.expect(AdapterException.class);

		String arrayText = "{'test' = [{'key'='alpha', 'val' = 'value1'},{'key'='beta', 'val' = 'value2'}]}";

		/* execute */
		String textFound = supportToTest.fetch("test",arrayText).fetchArrayElementHaving("val", Collections.singletonMap("key","beta")).asText();
		assertEquals("value2",textFound);

	}

}
