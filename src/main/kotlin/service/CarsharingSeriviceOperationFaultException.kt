package service

class CarsharingServiceOperationFaultException(private val errorCode: CarsharingServiceErrorCode) : Throwable() {
    override val message: String? =
        "${errorCode.message} throw on ${stackTrace.first().className}.${stackTrace.first().methodName}"
}