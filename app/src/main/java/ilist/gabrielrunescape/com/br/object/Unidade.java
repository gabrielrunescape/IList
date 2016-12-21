package ilist.gabrielrunescape.com.br.object;

/**
 * <h1>Objeto Unidade</h1>.
 *
 *
 *
 * @author Gabriel Filipe
 * @version 0.2
 * @since 2016-12-19
 */
public class Unidade {
    private long ID;
    private String descricao;
    private String abrevicao;

    /**
     * Método construtor simples.
     */
    public Unidade() {

    }

    /**
     * Método construtor com parametros.
     * @param descricao Descrição.
     * @param abrevicao Abreviação.
     */
    public Unidade(String descricao, String abrevicao) {
        this.abrevicao = abrevicao;
        this.descricao = descricao;
    }

    /**
     * Método construtor com parametros.
     * @param ID Código identificador.
     * @param descricao Descrição.
     * @param abrevicao Abreviação.
     */
    public Unidade(long ID, String descricao, String abrevicao) {
        this.abrevicao = abrevicao;
        this.descricao = descricao;
        this.ID = ID;
    }

    /**
     * @return Obtem a abreviação.
     */
    public String getAbrevicao() {
        return abrevicao;
    }

    /**
     * @param abrevicao Define abreviação.
     */
    public void setAbrevicao(String abrevicao) {
        this.abrevicao = abrevicao;
    }

    /**
     * @return Obtem a descrição.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao Define abreviação.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return Obtem o código identificador.
     */
    public long getID() {
        return ID;
    }

    /**
     * @param ID Define o código identificador.
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return descricao + " - " + abrevicao;
    }
}
