package ch.leytto.cynoclient.model

import ch.leytto.cynoclient.db.dao.ClientDao
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.Locality
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocality
import kotlinx.coroutines.flow.Flow

class ClientRepository(private val clientDao: ClientDao) : AbstractRepository() {
    val allClients: Flow<List<Client>> = clientDao.getClients()
    suspend fun insertClient(client: Client){
        clientDao.insert(client)
    }

    /**
     * Get all the client data of the given [id] from the dao
     */
    fun getById(id: String): ClientWithLocality {
        return clientDao.getById(id)
    }
}