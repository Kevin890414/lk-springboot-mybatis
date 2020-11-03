/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.example.demo.utils;


import java.util.Collection;
import java.util.Map;


public  class UtilValidate {

    public static final String module = UtilValidate.class.getName();


    public static boolean isNotEmpty(Object o) {
        return !ObjectType.isEmpty(o);
    }

    /** Check whether string s is empty. */
    public static boolean isEmpty(String s) {
        return (s == null) || s.length() == 0;
    }

    /** Check whether collection c is empty. */
    public static <E> boolean isEmpty(Collection<E> c) {
        return (c == null) || c.isEmpty();
    }

    /** Check whether map m is empty. */
    public static <K,E> boolean isEmpty(Map<K,E> m) {
        return (m == null) || m.isEmpty();
    }

    /** Check whether charsequence c is empty. */
    public static <E> boolean isEmpty(CharSequence c) {
        return (c == null) || c.length() == 0;
    }

    /** Check whether string s is NOT empty. */
    public static boolean isNotEmpty(String s) {
        return (s != null) && s.length() > 0;
    }

    /** Check whether collection c is NOT empty. */
    public static <E> boolean isNotEmpty(Collection<E> c) {
        return (c != null) && !c.isEmpty();
    }

    /** Check whether charsequence c is NOT empty. */
    public static <E> boolean isNotEmpty(CharSequence c) {
        return ((c != null) && (c.length() > 0));
    }

    public static boolean isString(Object obj) {
        return ((obj != null) && (obj instanceof java.lang.String));
    }


}
