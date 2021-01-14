<template>
    <div class="row">
    <div class="col-4 container-fluid">
        <h1>Agregar una tarea</h1>
        <form>
            <div>
                <label for="name">Nombre</label>
            <input type="text" id="nombre" v-model="newTarea.nombre">
            </div>



            <div>
                <label for="name">Voluntarios requeridos</label>
            <input type="text" id="nombre" v-model="newTarea.cant_vol_requeridos">
            </div>

            <div>
                <label for="name">Voluntarios asignados</label>
            <input type="text" id="nombre" v-model="newTarea.cant_vol_inscritos">
            </div>




            <div>
                <label for="descrip">Descripción</label>
            <input type="text" id="descrip" v-model="newTarea.descrip">
            </div>

            <div>
                <label for="finicio">Fecha de inicio</label>
            <input type="date" id="finicio" v-model="newTarea.finicio">
            </div>

            <div>
                <label for="ffin">Fecha de termino</label>
            <input type="date" id="ffin" v-model="newTarea.ffin">
            </div>

            <div>
                <label for="id_emergencia">Elegir la emergencia correspondiente</label>
            <select id="id_emergencia" v-model="newTarea.id_emergencia">
                <option v-bind:value="emergencia.id" v-for="(emergencia, index) in insts" :key="index">{{emergencia.nombre}}</option>
            </select>
            </div>

            <div class="form-group">
                <label for="latitud">Latitud</label>
            <input class="form-control" :placeholder="ubicacion.lat" :readonly="ubicacion.lat" id="latitud">
            </div>
            <div class="form-group">
                <label for="longitud">Longitud</label>
            <input class="form-control" :placeholder="ubicacion.lng" :readonly="ubicacion.lng" id="longitud">
            </div>
            <div>
                <button type="button" @click="send">Crear</button>
            </div>
            <div class="info">
                <h2>Objeto</h2>
                <code>{{newTarea}}</code>
                <p class="message">
                    {{message}}
                </p>
            </div>
        </form>
    </div>
        <div id="mapa" class="container col-6 map">
            <l-map :zoom="zoom" :center="center" @click="getUbicacion">
                <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
                <l-marker
                    :key="'tar' + index"
                    v-for="(tar, index) in tareas"
                    :lat-lng="posicion(tar.latitud, tar.longitud)"             
                >
                <l-icon
                    :icon-size="tarea_tamanio"
                    :icon-url="tarea_icono"
                >
                </l-icon>
                </l-marker>       
            </l-map>
        </div>
    </div>
</template>

<script>

import L from 'leaflet'
import { LMap, LTileLayer, LMarker, LIcon} from 'vue2-leaflet'
import tarea_icono from '../assets/tarea_icono.png'

export default {
    
    data(){
        return{
            url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStre,etMap</a> contributors',
            center: L.latLng(-33.561995, -70.562439),
            message:'',
            newTarea:{},
            insts:[],
            tareas:[],
            ubicacion: L.latLng(-33.561995, -70.562439),
            zoom: 7,
            tarea_icono: tarea_icono,
            tarea_tamanio: [18, 18]
        }
    },
    components: {
        LMap,
        LTileLayer,
        LMarker,
        LIcon
    },
    methods:{
        
        posicion: function(lat, lng){
            return L.latLng(lat, lng);
        },
        send:async function(){
            this.message = '';
            this.newTarea.latitud = this.ubicacion.lat;
            this.newTarea.longitud = this.ubicacion.lng;
            if(!this.newTarea.nombre){
                this.message = 'ingrese un nombre valido'
                return false
            }
            if(!this.newTarea.descrip){
                this.message = 'ingrese una descripcion'
                return false
            }

            if(!this.newTarea.cant_vol_requeridos){
                this.message = 'ingrese cantidad de voluntarios requeridos valida'
                return false
            }
            if(!this.newTarea.cant_vol_inscritos){
                this.message = 'ingrese una cantidad de voluntarios inscritos valida'
                return false
            }



            if(!this.newTarea.finicio){
                this.message = 'ingrese la fecha de inicio'
                return false
            }
            if(!this.newTarea.ffin){
                this.message = 'ingrese la fecha de termino'
                return false
            }
            if(!this.newTarea.id_emergencia){
                this.message = 'ingrese una emergencia correspondiente'
                return false
            }
            try {
                var result = await this.$http.post('/tareas', this.newTarea);
                let tarea = result.data;
                this.message = `Se creó una nueva tarea con id: ${tarea.id}`;
                this.newTarea = {};
            } catch (error) {
                console.log('error', error)
                this.message = 'no se ha podido crear la tarea'
            }
            this.todasTareas(); 
            
        },
        
        todasEmergenciass:async function(){
            try {
                let response = await this.$http.get('/emergencias');
                this.insts  = response.data;
                console.log(response);
            } catch (error) {
                console.log('error', error);
            }
        },
        todasTareas: async function(){
             try {
                let response = await this.$http.get('/tareas');
                this.tareas  = response.data;
                console.log(this.tareas);
            } catch (error) {
                console.log('error', error);
            }
        },
        getUbicacion: function(e){
          this.ubicacion = e.latlng;
      },         
    },
    created:function(){
        this.todasEmergenciass();
        this.todasTareas();
    }
}
</script>
<style lang="scss">
code{
    padding: .5rem 1rem;
    color:white;
    background:#444;
    font-size: 1rem;
    width: 12rem;
    display:inline-block;
}
p.message{
    margin:0 auto;
    width: 12rem;
    border:solid 1px rgba(0,0,0,0.25);
    padding: .5rem 1rem;
    font-weight: bold;
}
#map {
    position: relative;
    height: 500px;
    width: 500px;
}
</style>