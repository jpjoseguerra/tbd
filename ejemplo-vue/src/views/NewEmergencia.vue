<template>
<div class="container">
    <h1>Agregar una emergencia</h1>
    <form>
        <div>
            <label for="name">Nombre</label>
        <input type="text" id="nombre" v-model="newEmergencia.nombre">
        </div>

        <div>
            <label for="descrip">Descripción</label>
        <input type="text" id="descrip" v-model="newEmergencia.descrip">
        </div>

        <div>
            <label for="finicio">Fecha de inicio</label>
        <input type="date" id="finicio" v-model="newEmergencia.finicio">
        </div>

        <div>
            <label for="ffin">Fecha de termino</label>
        <input type="date" id="ffin" v-model="newEmergencia.ffin">
        </div>

        <div>
            <label for="id_institucion">Elegir la institución encargada</label>
        <select id="id_institucion" v-model="newEmergencia.id_institucion">
            <option v-bind:value="institution.id" v-for="(institution, index) in insts" :key="index">{{institution.nombre}}</option>
        </select>
        </div>

        <div>
            <button type="button" @click="send">Crear</button>
        </div>
        <div class="info">
            <h2>Objeto</h2>
            <code>{{newEmergencia}}</code>
            <p class="message">
                {{message}}
            </p>
        </div>
    </form>

</div>
</template>
<script>
export default {
    
    data(){
        return{
            message:'',
            newEmergencia:{},
            insts:[]
        }
    },
    methods:{
        send:async function(){
            this.message = '';
            if(!this.newEmergencia.nombre){
                this.message = 'Debe ingresar un nombre'
                return false
            }
            if(!this.newEmergencia.descrip){
                this.message = 'Debe ingresar una descripción'
                return false
            }
            if(!this.newEmergencia.finicio){
                this.message = 'Debe ingresar una fecha de inicio'
                return false
            }
            if(!this.newEmergencia.ffin){
                this.message = 'Debe ingresar una fecha de termino'
                return false
            }
            if(!this.newEmergencia.id_institucion){
                this.message = 'Debe ingresar una institución encargada de la emergencia'
                return false
            }
            try {
                var result = await this.$http.post('/emergencias', this.newEmergencia);
                let emergencia = result.data;
                this.message = `Se creó una nueva emergencia con id: ${emergencia.id}`;
                this.newEmergencia = {};
            } catch (error) {
                console.log('error', error)
                this.message = 'Ocurrió un error'
            }           
            
        },
        
        getInsts:async function(){
            try {
                let response = await this.$http.get('/instituciones');
                this.insts  = response.data;
                console.log(response)
            } catch (error) {
                console.log('error', error);
            }
        }            

    },
        created:function(){
        this.getInsts();
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
</style>