package ilist.gabrielrunescape.com.br.object;

/**
 * <h1>Objeto Item</h1>.
 *
 * Objeto onde serão armazenados informações do Item.
 *
 * @author Gabriel Filipe
 * @version 0.1
 * @since 2016-12-18
 */
public class Item {
    private long ID;
    private String nome;
    private String Status;

    /**
     * Construtor simples do objeto.
     */
    public Item() {

    }

    /**
     * Construtor do objeto.
     * @param nome Nome do item.
     * @param status Status do item.
     */
    public Item(String nome, String status) {
        this.nome = nome;
        Status = status;
    }

    /**
     * Construtor do objeto com parametros.
     * @param ID Código identificador.
     * @param nome Nome do item.
     * @param status Status do item.
     */
    public Item(long ID, String nome, String status) {
        this.ID = ID;
        this.nome = nome;
        Status = status;
    }

    /**
     * @return Código identificador do item.
     */
    public long getID() {
        return ID;
    }

    /**
     * @param ID Código identificador a ser inserido.
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * @return Nome do item.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Nome a ser inserido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Status como INTEGER (primary key) ou VARCHAR (view).
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param status Status como Integer ou VARCHAR.
     */
    public void setStatus(String status) {
        Status = status;
    }
}
