package cl.tbd.ejemplo1.models;

public class Ranking {
    private long id;
    private long id_voluntario;
    private long id_tarea;
    private int puntaje;
    private int flg_invitado;
    private int flg_participa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(long id_voluntario) {
        this.id_voluntario = id_voluntario;
    }

    public long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(long id_tarea) {
        this.id_tarea = id_tarea;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getFlg_invitado() {
        return flg_invitado;
    }

    public void setFlg_invitado(int flg_invitado) {
        this.flg_invitado = flg_invitado;
    }

    public int getFlg_participa() {
        return flg_participa;
    }

    public void setFlg_participa(int flg_participa) {
        this.flg_participa = flg_participa;
    }
}
