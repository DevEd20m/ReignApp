package com.faztbit.domain.utils

sealed class Failure {

    /** 404*/
    open class ResourceNotFound() : Failure()

    /** Extend this class for feature specific SERVICE ERROR BODY RESPONSE.*/
    open class ServerError(val code: Int, val message: String) : Failure()

    /** When suddenly the connection is lost.*/
    object NetworkConnectionLostSuddenly : Failure()

    /** When there is no internet network detected.*/
    object NoNetworkDetected : Failure()

    object SSLError : Failure()

    open class DataBaseError(val message: String?) : Failure()

    /** When service is taking to long on return the response.*/
    object TimeOut : Failure()

    /** Extend this class for feature specific failures.*/
    open class ServiceUncaughtFailure(val uncaughtFailureMessage: String) : Failure()

    /** error server not mapped */
    open class ErrorServerNotMapped(val code: Int, val message: String) : Failure()

    /** error server not mapped */
    open class ErrorNotMapped() : Failure()

    /** Extend this class for feature specific DATA -> DOMAIN MAPPERS exceptions.*/
    open class DataToDomainMapperFailure(val mapperException: String?) : Failure()

    /** Extend this class for feature specific DOMAIN -> PRESENTATION MAPPERS exceptions.*/
    open class DomainToPresentationMapperFailure(val mapperException: String?) : Failure()

}