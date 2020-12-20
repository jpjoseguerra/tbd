import time
import psycopg2
import json
import random
import time 
import datetime

current_milli_time = lambda: int(round(time.time() * 1000))

#formato AAAA-MM-DD
def rand_date(iniDate, endDate):

  initstp = time.mktime(datetime.datetime.strptime(iniDate, "%Y-%m-%d").timetuple())
  endtstp = time.mktime(datetime.datetime.strptime(endDate, "%Y-%m-%d").timetuple())
  newDate = random.randrange( initstp, endtstp )
  newDateTimestamp = datetime.datetime.fromtimestamp(newDate)
  
  return newDateTimestamp.strftime("%Y-%m-%d")

def get_connection_db(dbName):
    return psycopg2.connect(user="postgres", password="postgres", host="127.0.0.1", port="5432", database=dbName)

def get_data(table, dbName):
    obj_ids = []

    connection = get_connection_db(dbName)
    cursor = connection.cursor()

    cursor.execute("SELECT * FROM " + table)
    for record in cursor:
        obj_ids.append(record[0])
    
    return obj_ids

def fill_voluntario(quantity, dbName):

    names = ["Ana", "Enzo", "Hugo", "Juan", "Lara", "Leo", "Luz", "Sara", "Hector", "Helena", "Helena", "Lucia", "Manuel", "Mariana", "Martin", "Martin", "Isabel", "Lucia", "Marta", "Moises", "Raquel", "Samuel", "Alberto", "Felipe", "Beatriz", "Daniela", "Claudia", "Laura", "Daniel", "Pablo", "Alvaro", "Mateo", "Matias", "Diego", "David", "Sergio", "Marcos", "Carlos", "Miguel", "Lucas", "Mario", "Valentina"]

    last_names = ["Gonzalez", "Rodriguez", "Gomez", "Lopez", "Fernandez", "Romero", "Sanchez", "Garcia", "Perez", "Martinez", "Diaz", "Sosa", "Torres", "Alvarez", "Ruiz", "Ramirez", "Acosta", "Suarez", "Gutierrez", "Aguire", "Montes", "Gimenez", "Rojas", "Pereyra", "Molina", "Castro", "Ortiz", "Silva", "Juarez", "Ojeda", "Ponce", "Arias", "Figueroa", "Ramos", "Correa", "Hernandez", "Escobar", "Mendoza", "Caroca", "Muena", "Campos", "Soto"]

    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, quantity):
            full_name = random.choice(names) + " " + random.choice(last_names)
            birthday = rand_date("1970-01-01", "2002-01-01")
            cursor.execute("INSERT INTO voluntario (nombre, fnacimiento) VALUES (%s, %s)",
                            ( full_name, birthday ) )

        connection.commit()
        print("Se agregaron " + str(quantity) + " registros en la tabla voluntario")     


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en la tabla voluntario:")
            print("\t" + str(error))
      

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")

def fill_institucion(dbName):

    names = ["Techo", "Cruz Roja", "Voluntariado 10 de Octubre", "CANEC", "Trabajo en la Calle", "YMCA", "Greenpeace", "America Solidaria", "Hogar de Cristo", "COANIL", "COANIQUEM"]

    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, len(names)):
            name = names[i]
            
            cursor.execute("INSERT INTO institucion (nombre, descrip) VALUES (%s, %s)",
                            ( name, name ) )

        connection.commit()
        print("Se agregaron " + str(len(names)) + " registros en la tabla institucion")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en la tabla institucion:")
            print("\t" + str(error))           
      

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")


def fill_habilidad(dbName):
    
    descriptions = ["Ser bueno con ninos", "Ser bueno con animales", "Tener fuerza", "Tiempo para largas jornadas", "Paciencia con tercera edad", "Buen estado fisico", "Capacidad para educar", "Capacidad para entretener"]
    
    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, len(descriptions)):
            description = descriptions[i]
            cursor.execute("INSERT INTO habilidad (descrip) VALUES (%s)",
                            (description, ) )

        connection.commit()
        print("Se agregaron " + str(len(descriptions)) + " registros en la tabla habilidad")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla habilidad: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")

def fill_estado_tarea(dbName):
    
    descriptions = ["Finalizada", "Pendiente", "En proceso", "Cancelada"]
    
    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, len(descriptions)):
            description = descriptions[i]
            cursor.execute("INSERT INTO estado_tarea (descrip) VALUES (%s)",
                            (description, ) )

        connection.commit()
        print("Se agregaron " + str(len(descriptions)) + " registros en la tabla estado_tarea")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla estado_tarea: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")

def fill_emergencia(dbName):

    ids = []

    names = ["Incendio", "Terremoto", "Falta de personal", "Derrumbe", "Aluvion"]
    descriptions = ["Incendio que afecto a varias casa en comuna de Santiago", "Fuerte terremoto afecto a octava region", "Se necesitan voluntarios para atender casa de ancianos", "Remota localidad cordillerana fue afectada por un derrumbe", "Rio Las Minas desbordado"]
    
    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        cursor.execute("SELECT * FROM institucion")
        for record in cursor:
            ids.append(record[0])
        
        for i in range(0, len(names)):
            name = names[i]
            description = descriptions[i]
            fstart = rand_date("1970-01-01", "2002-01-01")
            fend = rand_date(fstart, "2002-05-05")
            idinst = random.choice(ids)
            cursor.execute("INSERT INTO emergencia (nombre, descrip, finicio, ffin, id_institucion) VALUES (%s, %s, %s, %s, %s)",
                            (name, description, fstart, fend, idinst) )       

        connection.commit()
        print("Se agregaron " + str(len(names)) + " registros en la tabla emergencia")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla emergencia: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")



def fill_tarea(quantity, dbName):
    
    names = ["Cuidar ninos", "Cuidar animales", "Cuidar adultos mayores", "Levantar escombros", "Contener fuego", "Llevar alimentos", "Ensenar"]
    ids_eme = get_data("emergencia", dbName)
    ids_est = get_data("estado_tarea", dbName)
    
    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, quantity):
            name = random.choice(names)
            requeridos = random.randint(1, 50)
            inscritos = random.randint(0, requeridos)
            id_eme = random.choice(ids_eme)
            cursor.execute("SELECT finicio, ffin FROM emergencia WHERE id = %s", (id_eme, ))
            fechas = cursor.fetchone() #inicio en 0, fin en 1
            finicio = rand_date(str(fechas[0]), str(fechas[1]))
            ffin = rand_date(str(finicio), str(fechas[1]))
            id_est = random.choice(ids_est)
            cursor.execute("INSERT INTO tarea (nombre, descrip, cant_vol_requeridos, cant_vol_inscritos, id_emergencia, finicio, ffin, id_estado) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)",
                            (name, name, requeridos, inscritos, id_eme, finicio, ffin, id_est))

        connection.commit()
        print("Se agregaron " + str(quantity) + " registros en la tabla tarea")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla tarea: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")

def fill_eme_habilidad(quantity, dbName):

    ids_hab = get_data("habilidad", dbName)
    ids_eme = get_data("emergencia", dbName)

    already_posted = []
    

    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, quantity):
            pair = []
            id_h = random.choice(ids_hab)
            id_e = random.choice(ids_eme)
            pair.append(id_h)
            pair.append(id_e)        

            if(pair not in already_posted):
                cursor.execute("INSERT INTO eme_habilidad (id_emergencia, id_habilidad) VALUES (%s, %s)", 
                                (id_e, id_h) )
                

            already_posted.append(pair)
            

        connection.commit()
        print("Se agregaron " + str(quantity) + " registros en la tabla eme_habilidad")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla eme_habilidad: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")
    
def fill_tarea_habilidad(quantity, dbName):

    ids_emehab = get_data("eme_habilidad", dbName)
    ids_tarea = get_data("tarea", dbName)

    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, quantity):
            id_emehab = random.choice(ids_emehab)
            id_tarea = random.choice(ids_tarea)

            cursor.execute("INSERT INTO tarea_habilidad (id_emehab, id_tarea) VALUES (%s, %s)", 
                            (id_emehab, id_tarea) )

        connection.commit()
        print("Se agregaron " + str(quantity) + " registros en la tabla tarea_habilidad")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla tarea_habilidad: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")

def fill_vol_habilidad(quantity, dbName):

    ids_vol = get_data("voluntario", dbName)
    ids_hab = get_data("habilidad", dbName)

    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, quantity):
            id_vol = random.choice(ids_vol)
            id_hab = random.choice(ids_hab)

            cursor.execute("INSERT INTO vol_habilidad (id_voluntario, id_habilidad) VALUES (%s, %s)", 
                            (id_vol, id_hab) )

        connection.commit()
        print("Se agregaron " + str(quantity) + " registros en la tabla vol_habilidad")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla vol_habilidad: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")
            
def fill_ranking(quantity, dbName):

    ids_vol = get_data("voluntario", dbName)
    ids_tarea = get_data("tarea", dbName)

    try:	
        connection = get_connection_db(dbName)
        cursor = connection.cursor()

        for i in range(0, quantity):
            id_vol = random.choice(ids_vol)
            id_tarea = random.choice(ids_tarea)
            cursor.execute("SELECT id_habilidad FROM vol_habilidad WHERE id_voluntario = %s", (id_vol, ))
            vol_habs = cursor.fetchall()
            cursor.execute("SELECT id_habilidad FROM eme_habilidad WHERE id IN (SELECT id_emehab FROM tarea_habilidad WHERE id_tarea = %s)", (id_vol, ))
            tarea_habs = cursor.fetchall()
            print(id_vol, vol_habs)
            print('\n')
            print(id_tarea, tarea_habs)
            print('\n')
            print('\n')
            #puntaje = len(set(tarea_habs) & set(vol_habs))
            puntaje = random.randint(0,10)
            invitado = random.randint(0, 1)
            if invitado == 1: 
            	participa = random.randint(0, 1)
            else: 
            	participa = 0
            cursor.execute("INSERT INTO ranking (id_voluntario, id_tarea, puntaje, flg_invitado, flg_participa) VALUES (%s, %s, %s, %s, %s)", 
                            (id_vol, id_tarea, puntaje, invitado, participa) )

        connection.commit()
        print("Se agregaron " + str(quantity) + " registros en la tabla ranking")   


    except (Exception, psycopg2.Error) as error :
        if(connection):
            print("Error en el ingreso de datos a tabla ranking: ")
            print("\t" + str(error))         

    finally:
        #closing database connection.
        if(connection):
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")
                      

dbName_f = "postgres"
fill_voluntario(1000, dbName_f)
fill_institucion(dbName_f)
fill_habilidad(dbName_f)
fill_estado_tarea(dbName_f)
fill_emergencia(dbName_f)
fill_tarea(25, dbName_f)
fill_eme_habilidad(200, dbName_f)
fill_tarea_habilidad(200, dbName_f)
fill_vol_habilidad(200, dbName_f)
fill_ranking(1000, dbName_f)
