package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.edu.ulima.pm20232.aulavirtual.configs.BackendClient
import pe.edu.ulima.pm20232.aulavirtual.models.BodyPart
import pe.edu.ulima.pm20232.aulavirtual.models.Exercise
import pe.edu.ulima.pm20232.aulavirtual.models.responses.BodyPartExercisesCount
import pe.edu.ulima.pm20232.aulavirtual.services.*

class RoutineScreenViewModel(): ViewModel(){
    private val memberService = BackendClient.buildService(MemberService::class.java)
    private val coroutine: CoroutineScope = viewModelScope

    var userId: Int by mutableStateOf(0)
    var memberId: Int by mutableStateOf(0)
    var bodyPartsCount: Int by mutableStateOf(0)
    var exercisesCount: Int by mutableStateOf(0)
    val bodyPartMap = mutableMapOf<Int, String>()
    private var _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> get() = _exercises
    fun setExercises(newItems: List<Exercise>) {
        _exercises.value = newItems
    }

    fun fetchBodyPartsExercises(){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = memberService.exercisesBodyParts(memberId).execute()
                    if (response.isSuccessful) {
                        val response: BodyPartExercisesCount = response.body()!!
                        println(response)
                        bodyPartsCount = response.bodyParts
                        exercisesCount = response.exercises
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun fetchBodyParts(){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = memberService.bodyParts(memberId).execute()
                    if (response.isSuccessful) {
                        val list: List<BodyPart> = response.body()!!
                        for(g: BodyPart in list){
                            bodyPartMap[g.id] = g.name
                        }
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun fetchExercieses(){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = memberService.exercises(memberId).execute()
                    if (response.isSuccessful) {
                        val list: List<Exercise> = response.body()!!
                        setExercises(list)
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    fun filterByBodyParts(bodyPartId: Int){
        val service: ExerciseService = ExerciseService()
        val list = service.exerciseListByBodyPartId(bodyPartId)
        setExercises(list)
    }
}