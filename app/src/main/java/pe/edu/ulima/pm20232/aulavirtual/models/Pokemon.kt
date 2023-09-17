package pe.edu.ulima.pm20232.aulavirtual.models

data class Pokemon (
    val id: Long,
    val name: String,
    val number: Long,
    val weight: Double,
    val height: Double,
    val imageUrl: String,
    val generationId: Long,
    val generationName: String
)