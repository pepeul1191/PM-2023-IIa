package pe.edu.ulima.pm20232.aulavirtual.services

import pe.edu.ulima.pm20232.aulavirtual.models.Level

class LevelService {
    var bodyPartList: ArrayList<Level> = ArrayList<Level>()

    constructor(){
        bodyPartList.add(Level(id = 1, name = "SUPERIOR HOMBRE - PRINCIPIANTE"))
        bodyPartList.add(Level(id = 2, name = "SUPERIOR HOMBRE - INTERMEDIO"))
    }
}