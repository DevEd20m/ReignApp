package com.faztbit.data.repository

import com.faztbit.data.base.CoroutineTestRule
import com.faztbit.data.mapper.MainDataMapper
import com.faztbit.data.mapper.MainDataMapperImpl
import com.faztbit.data.source.database.dataSource.MainDataBaseDataSource
import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.data.source.services.dataSource.MainServiceDataSource
import com.faztbit.data.source.services.response.HitsHeaderResponse
import com.faztbit.data.source.services.response.HitsMainResponse
import com.faztbit.data.utils.ConnectionUtils
import com.faztbit.domain.repository.MainRepository
import com.faztbit.domain.utils.Either
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryImplTest {


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()


    @Mock
    lateinit var service: MainServiceDataSource

    @Mock
    lateinit var database: MainDataBaseDataSource

    lateinit var mapper: MainDataMapper

    @Mock
    lateinit var connection: ConnectionUtils


    lateinit var repository: MainRepository


    @Before
    fun setUp() {
        mapper = MainDataMapperImpl()
        repository = MainRepositoryImpl(service, database, mapper, connection)
    }


    @Test
    fun `fetch Hits from Server when has Internet`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            val dataToReturn = Either.Success(mocketHitsResponse)
            whenever(connection.isNetworkAvailable()).thenReturn(true)
            whenever(service.fetchHitsByQuery("")).thenReturn(dataToReturn)
            whenever(
                database.saveHits(
                    mapper.mapHitsDomainToDb(
                        mapper.mapHitsResponseToDomain(
                            mocketHitsResponse
                        )
                    )
                )
            ).thenReturn(Either.Success(Unit))

            val result = repository.fetchHitsByQuery("")
            Assert.assertEquals(
                "dsds",
                if (result is Either.Success) result.success[0].objectId else null
            )
            Assert.assertEquals(true, result.isSuccess)
        }


    @Test
    fun `fetch Hits from DataBase when has not Internet`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            val mapperResult = mapper.mapHitsDbToDomain(listOf(mockedHitsDb))
            whenever(connection.isNetworkAvailable()).thenReturn(false)
            whenever(database.getHitsSaved()).thenReturn(Either.Success(mockedHitsDataBase))

            val result = repository.fetchHitsByQuery("")
            Assert.assertEquals(true, result.isSuccess)
            Assert.assertEquals(result, Either.Success(mapperResult))
        }


    val mockedHits = HitsMainResponse("dsds", "dsds", "dsd", "ddsds", "sds")
    val mockedHitsDb = HitsDb("dsds", "dsds", "dsd", "ddsds", "sds")
    val mocketHitsResponse = HitsHeaderResponse(listOf(mockedHits))
    val mockedHitsDataBase = listOf(HitsDb("dsds", "dsds", "dsd", "ddsds", "sds"))

}