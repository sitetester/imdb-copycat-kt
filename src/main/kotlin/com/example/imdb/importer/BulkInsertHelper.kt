package com.example.imdb.importer

import org.hibernate.SessionFactory
import org.springframework.stereotype.Component

@Component
class BulkInsertHelper(private val sessionFactory: SessionFactory) {

    // https://stackoverflow.com/questions/7349464/bulk-insert-or-update-with-hibernate
    fun bulkInsert(entities: List<Any>) {

        val session = sessionFactory.openSession()
        val tx = session.beginTransaction()

        entities.forEach { entity ->
            session.save(entity)
        }

        session.flush()
        session.clear()

        tx.commit()
        session.close()
    }
}