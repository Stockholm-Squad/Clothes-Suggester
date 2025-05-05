package logic.model

data class LocationModel(
    val ip: String?,
    val latitude: Double?,
    val longitude: Double?,
    val city: String?,
    val country: String?,
    val regionName: String?,
    val zip: String?,
    val timezone: String?
)