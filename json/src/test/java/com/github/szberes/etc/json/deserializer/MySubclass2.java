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

class MySubclass2 extends MyParentClass {
	public String getFieldInMySubClass1() {
		return fieldInMySubClass1;
	}

	private String fieldInMySubClass1;

	private MySubclass2(String fieldInMySubClass1) {
		super("MySubclass2");
		this.fieldInMySubClass1 = fieldInMySubClass1;
	}
}
