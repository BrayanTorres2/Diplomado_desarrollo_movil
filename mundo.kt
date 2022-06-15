//--------------------- VERIFIQUEN SI TODOS LOS PUNTOS ESTAN, EN CASO QUE NO DEBEN REALIZAR LOS PUNTOS QUE FALTEN---------------
import ean.collections.ArrayList
import ean.collections.IList
import ean.collections.LinkedList
import kotlin.math.abs

class Ubicacion_Geografica_Punto:Comparable<Ubicacion_Geografica_Punto> {


    private var calle = 0
    private var carrera = 0

    constructor()
    constructor(calle: Int, carrera: Int) {
        this.calle = calle
        this.carrera = carrera
    }

    //Analizadoras
    fun darCalle() = calle
    fun darCarrrera() = carrera

    //Modificadoras
    fun nuevaCalle(c:Int){
        calle=c
    }
    fun nuevaCarrera(c:Int){
        carrera=c
    }

    //Comparador
    override fun compareTo(other: Ubicacion_Geografica_Punto): Int {
        if(this.darCalle()>other.darCalle()){
            return 1
        }
        else if(other.darCalle()>this.darCalle()){
            return -1
        }
        else{
            if (this.darCarrrera()>other.darCarrrera()){
                return 1
            }
            else if(this.darCarrrera()<other.darCarrrera()){
                return -1
            }
            else{
                return 0
            }
        }

    }
}

fun DistanciaManhattan(Inicio: Ubicacion_Geografica_Punto,Final: Ubicacion_Geografica_Punto):Int{
    return (Final.darCalle()-Inicio.darCalle())+(Final.darCarrrera()-Inicio.darCarrrera())
}

class Ambulacia {
    private var codigo = 0
    private var estado = false
    private var ubicacion_GeograficaA = Ubicacion_Geografica_Punto()
    private var accidentado: Accidentado? = null

    constructor()
    constructor(codigo: Int, estado: Boolean, ubicacion_GeograficaA: Ubicacion_Geografica_Punto, accidentado: Accidentado?) {
        this.codigo = codigo
        this.estado = estado
        this.ubicacion_GeograficaA = ubicacion_GeograficaA
        this.accidentado = accidentado
    }

    //Analizadores
    fun darcodigo() = codigo
    fun darestado() = estado
    fun darubicacion() = ubicacion_GeograficaA
    fun daraccidentado() = accidentado

    //Modificadoras
    fun NuevoEstadoAM(e:Boolean){
        estado=e
    }

    //Metodos
    fun IngresarUnAccidentado(A:Accidentado){
        require(estado==false)
        accidentado=A
        estado=true
    }
    fun desocuparAmbulancia(){
        require(estado==true)
        estado = false
        accidentado = null
    }
    fun CambiarUbicacionAmbulancia( newUbi : Ubicacion_Geografica_Punto){
        ubicacion_GeograficaA = newUbi
    }
}

class Accidentado:Comparable<Accidentado>{
    private var NombrePaciente=""
    private var MotivoAccidente=""
    private var ubicacion_geograficaAC=Ubicacion_Geografica_Punto()
    constructor()
    constructor(NombrePaciente: String, MotivoAccidente: String,ubicacion_GeograficaAC: Ubicacion_Geografica_Punto) {
        this.NombrePaciente = NombrePaciente
        this.MotivoAccidente = MotivoAccidente
        this.ubicacion_geograficaAC=ubicacion_GeograficaAC
    }
    fun darNombrePaciente()=NombrePaciente
    fun darMotivoAccidente()=MotivoAccidente
    fun darubicacion_geograficaAC()=ubicacion_geograficaAC
    override fun compareTo(other: Accidentado): Int {
        if (this.NombrePaciente>other.NombrePaciente){
            return 1
        }
        else if (other.NombrePaciente>this.NombrePaciente){
            return -1
        }
        else{
            return 0
        }
    }
}

class Hospital:Comparable<Hospital> {
    private var codigo = 0
    private var nombre = ""
    private var Hospitalizados = ArrayList<Accidentado>()
    private var tipo1 = ""
    private var tipo2 = ""
    private var ubicacion_GeograficaH = Ubicacion_Geografica_Punto()

    constructor()
    constructor(codigo: Int, nombre: String, Hospitalizados: ArrayList<Accidentado>, tipo1: String, tipo2: String, ubicacion_GeograficaH: Ubicacion_Geografica_Punto) {
        this.codigo = codigo
        this.nombre = nombre
        this.Hospitalizados = Hospitalizados
        this.tipo1 = tipo1
        this.tipo2 = tipo2
        this.ubicacion_GeograficaH = ubicacion_GeograficaH
    }

    //Analizadoras
    fun darcodigo() = codigo
    fun darnombre() = nombre
    fun darhospitalizados() = Hospitalizados
    fun dartipo1() = tipo1
    fun dartipo2() = tipo2
    fun darubicacion_GeograficaH() = ubicacion_GeograficaH

    //Metodos
    fun PacienteEstaEnElHospital(nombre: String):Boolean{
        for (i in Hospitalizados){
            if(i.darNombrePaciente()==nombre){
                return true
            }
        }
        return false
    }
    fun DeterminadoAccidente(n:String):Boolean{
        if (dartipo1()==n||dartipo2()==n){
            return true
        }
        return false
    }
    fun IngresarAccidentado(a:Accidentado){
        require(a.darMotivoAccidente()==dartipo1()||a.darMotivoAccidente()==dartipo2())
        var b=0
        for (i in Hospitalizados){
            if (i==a){
                b++
            }
        }
        if (b==0){
            Hospitalizados.add(a)
        }
    }
    fun DarDeAltaPaciente(n:String){
        for (i in 0 until Hospitalizados.size){
            if (Hospitalizados[i].darNombrePaciente()==n){
                Hospitalizados.remove(i)
            }
        }
    }

    //Comparador
    override fun compareTo(other: Hospital): Int {
        if (this.darcodigo()>other.darcodigo()){
            return 1
        }
        else if (other.darcodigo()>this.darcodigo()){
            return -1
        }
        else{
            return 0
        }
    }
}

object SistemaDeUrgencias{
    var ListadeHospitales=ArrayList<Hospital>()
    var ListadeAmbulacias=ArrayList<Ambulacia>()

    //Metodos
    fun AgregarAmbulancia(codigo: Int,calle: Int,carrera: Int){
        fun VerificarAmbulancia(codigo: Int):Boolean {
            for (i in ListadeAmbulacias) {
                if (i.darcodigo() == codigo) {
                    return true
                }
            }
            return false
        }
        var a=VerificarAmbulancia(codigo)
        if (a==false) {
            ListadeAmbulacias.add(Ambulacia(codigo, false, Ubicacion_Geografica_Punto(calle, carrera), null))
        }
    }

    fun AgregarHospital(nombre: String,codigo: Int,calle: Int,carrera: Int,tipo1: String,tipo2: String){
        fun VerificarHospital(codigo: Int):Boolean {
            for (i in ListadeHospitales) {
                if (i.darcodigo() == codigo) {
                    return true
                }
            }
            return false
        }
        var a=VerificarHospital(codigo)
        if(a==false){
            ListadeHospitales.add(Hospital(codigo,nombre,ArrayList<Accidentado>(),tipo1,tipo2, Ubicacion_Geografica_Punto(calle,carrera)))
        }
    }

    fun OcurriounAccidente(ac:Accidentado):Ambulacia?{
        var a=ListadeAmbulacias.filter { it.darestado()==false}
        if (a.size==0){
            return null
        }
        else {
            var Ambul=a[0]
            for (i in 1 until a.size) {
                if (DistanciaManhattan(ac.darubicacion_geograficaAC(),a[i].darubicacion()) < DistanciaManhattan(ac.darubicacion_geograficaAC(),Ambul.darubicacion())) {
                    Ambul = a[i]
                }
            }
            return Ambul
        }
    }

    fun ActualizarUbicacionAmbulancia(CodigoAmbulancia:Int,NuevaUbicacion:Ubicacion_Geografica_Punto){
        for (i in ListadeAmbulacias){
            if (i.darcodigo()==CodigoAmbulancia && i.darestado()==false){
                i.CambiarUbicacionAmbulancia(NuevaUbicacion)
            }
        }
    }

    fun AsignarAccidentado(A: Accidentado,AM:Ambulacia){
        require(AM.darestado()==false)
        AM.IngresarUnAccidentado(A)
        AM.NuevoEstadoAM(true)
    }

    fun BuscarHospitalparaAmbulancia(amb:Ambulacia):Hospital?{
        require(amb.darestado()==true)
        var a=ListadeHospitales.filter { it.DeterminadoAccidente(amb.daraccidentado()!!.darMotivoAccidente())==true }
        if (a.size==0){
            return null
        }
        else{
            for (i in 0 until a.size){
                if (a[i].PacienteEstaEnElHospital(amb.daraccidentado()!!.darNombrePaciente())==true){
                    a.remove(i)
                }
            }
            if (a.size==0){
                return null
            }
            else{
                var Hospt=a[0]
                for (i in 1 until a.size) {
                    if (DistanciaManhattan(amb.darubicacion(),a[i].darubicacion_GeograficaH()) < DistanciaManhattan(amb.darubicacion(),Hospt.darubicacion_GeograficaH())) {
                        Hospt = a[i]
                    }
                }
                return Hospt
            }
        }
    }

    fun LlegadaAmbulanciaAHospital(Alc:Ambulacia){
        require(Alc.darestado()==true)
        var Busqueda=BuscarHospitalparaAmbulancia(Alc)
        var x=0
        for (i in Busqueda!!.darhospitalizados()) {
            if (i.darNombrePaciente() == Alc.daraccidentado()!!.darNombrePaciente()) {
                x++
            }
        }
        if(x==0) {
            Alc.desocuparAmbulancia()
            Alc.CambiarUbicacionAmbulancia(Busqueda.darubicacion_GeograficaH())
            Busqueda.IngresarAccidentado(Alc.daraccidentado()!!)
        }
    }

    fun DarDeAltaUnAccidentado(c:Int,n:String){
        for (i in 0 until ListadeHospitales.size){
            if(ListadeHospitales[i].darcodigo()==c ){
                ListadeHospitales[i].DarDeAltaPaciente(n)
            }
        }
    }








    //FUNCIONES EXTRA
    fun decodigoaAmbu(codez:Int):Ambulacia?{
        for(i in 0 until ListadeAmbulacias.size){
            if(ListadeAmbulacias[i].darcodigo()==codez){
                return ListadeAmbulacias[i]
            }
        }
        return null
    }

    fun arrayAmbu():Array<Int>{
        val resultado=Array<Int>(ListadeAmbulacias.size){ 0 }
        for(i in 0 until ListadeAmbulacias.size){
            resultado[i]= ListadeAmbulacias[i].darcodigo()
        }
        return resultado
    }

    fun arrayHospi():Array<Int>{
        val resultado=Array<Int>(ListadeHospitales.size){ 0 }
        for(i in 0 until ListadeHospitales.size){
            resultado[i]= ListadeHospitales[i].darcodigo()
        }
        return resultado
    }

    fun arrayAmbuLIB():Array<Int>{
        val lib= ListadeAmbulacias.filter { it.darestado()==false }
        val resultado=Array<Int>(lib.size){ 0 }
        for(i in 0 until lib.size){
            resultado[i]= lib[i].darcodigo()
        }
        return resultado
    }

    fun arrayAmbuFULL():Array<Int>{
        val lib= ListadeAmbulacias.filter { it.darestado()==true }
        val resultado=Array<Int>(lib.size){ 0 }
        for(i in 0 until lib.size){
            resultado[i]= lib[i].darcodigo()
        }
        return resultado
    }

    fun VerificarHospitalzinho(codigo: Int):Hospital? {
        for (i in ListadeHospitales) {
            if (i.darcodigo() == codigo) {
                return i
            }
        }
        return null
    }

    fun ending(c:Int):Array<String>{
        val resultado=Array<String>(ListadeHospitales.size){ "" }
        var r=Hospital()
        for(i in 0 until ListadeHospitales.size){
            if(ListadeHospitales[i].darcodigo()==c){
                r= ListadeHospitales[i]
            }
        }
        for (j in 0 until r.darhospitalizados().size){
            resultado[j]=r.darhospitalizados()[j].darNombrePaciente()
        }
        return resultado
    }









}
