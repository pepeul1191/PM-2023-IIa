package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService

class HomeScreenViewModel: ViewModel(){
    var pokemons = mutableStateListOf<Pokemon>()
    val generationsMap = mutableMapOf<Int, String>()

    fun listAll(){
        val pokemonService: PokemonService = PokemonService()
        val list = pokemonService.listAll()
        pokemons.clear()
        for (pokemon in list){
            pokemons.add(pokemon)
        }
    }

    fun getGenerations(){
        for(p: Pokemon in pokemons){
            val generationId = p.generationId
            val generationName = p.generationName
            if(!generationsMap.containsKey(generationId)){
                generationsMap[generationId] = generationName
            }
        }
    }

    fun filterByGenerations(generationId: Int){
        val pokemonService: PokemonService = PokemonService()
        val list = pokemonService.pokemonListByGenerationId(generationId)
        println("generationId: " + generationId )
        println("pokemons size: " + list.size )
        list.forEach{pokemon ->
            if(generationId == pokemon.generationId) {
                pokemons.add(pokemon)
            }
        }
    }
}