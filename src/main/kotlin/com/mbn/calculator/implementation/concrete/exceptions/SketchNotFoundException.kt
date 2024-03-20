package com.mbn.calculator.implementation.concrete.exceptions

import java.lang.RuntimeException

class SketchNotFoundException(message: String) : RuntimeException(message)

open class PersistenceException(message: String) : RuntimeException(message)

class MongoException(message: String) : PersistenceException(message)

class MySqlException(message: String) : PersistenceException(message)