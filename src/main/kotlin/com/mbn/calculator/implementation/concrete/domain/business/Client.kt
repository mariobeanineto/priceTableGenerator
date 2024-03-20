package com.mbn.calculator.implementation.concrete.domain.business

import com.mbn.calculator.implementation.concrete.domain.persistance.ClientMongo
import com.mbn.calculator.implementation.concrete.domain.persistance.ClientMySql

data class Client(
    val documentNumber: String,
    val name: String
) {
    companion object {
        fun from(clientMongo: ClientMongo): Client {
            return Client(
                documentNumber = clientMongo.documentNumber,
                name = clientMongo.name
            )
        }

        fun from(clientMySql: ClientMySql): Client {
            return Client(
                documentNumber = clientMySql.documentNumber,
                name = clientMySql.name
            )
        }
    }
}
