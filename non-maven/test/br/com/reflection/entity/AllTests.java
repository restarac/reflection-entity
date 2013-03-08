/**
 * *********************************************************************
 * Module: BaseTestCase.java Author: Frederico Purpose: Defines class that can
 * run all unit tests by its main function
 * *********************************************************************
 */
package br.com.reflection.entity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ReflectionColumnTest.class,
    ReflectionColumnIdTest.class,
    ReflectionEntityTest.class,
    ReflectionJoinTest.class,
    ReflectionJoinsTest.class,
    ReflectionTableNameTest.class
})
public class AllTests {
}