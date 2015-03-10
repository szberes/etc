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
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonClassTreeDeserializer<T> extends JsonDeserializer<T> {

	private ClassMapper<T> classMapper;

	public JsonClassTreeDeserializer(ClassMapper<T> classMapper) {
		this.classMapper = classMapper;
	}

	@Override
	public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		JsonNode jsonNode = jsonParser.readValueAsTree();
		try {
			Class<? extends T> targetClass = classMapper.map(jsonNode);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonNode, targetClass);
		} catch (ClassNotFoundException e) {
			throw new JsonMappingException(e.getMessage());
		}
	}
}
