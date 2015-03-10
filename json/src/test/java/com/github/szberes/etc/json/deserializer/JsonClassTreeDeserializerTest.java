/*
 * Copyright 2015 Szabolcs Balazs Beres.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.szberes.etc.json.deserializer;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JsonClassTreeDeserializerTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		SimpleModule module = new SimpleModule("name", new Version(1, 0, 0, ""));
		JsonClassTreeDeserializer<MyInterFace> deserializer = new JsonClassTreeDeserializer<MyInterFace>(new ClassMapper<MyInterFace>() {
			@Override
			public Class<? extends MyInterFace> map(JsonNode jsonNode) throws ClassNotFoundException {
				return (Class<? extends MyInterFace>) Class.forName(jsonNode.get("type").getTextValue());
			}
		});
		module.addDeserializer(MyInterFace.class, deserializer);
		mapper.registerModule(module);
	}

	@Test
	public void testDeserialize() throws Exception {
		MyInterFace randomObject = new MySubclass1("someString");

		String serialized = serialize(randomObject);
		MyInterFace deserialized = deserialize(serialized);

		assertEquals("someString", ((MySubclass1) deserialized).getFieldInMySubClass1());
	}

	private String serialize(MyInterFace randomObject) throws IOException {
		return mapper.writeValueAsString(randomObject);
	}

	private MyInterFace deserialize(String string) throws IOException {
		return mapper.readValue(string, MyInterFace.class);
	}
}