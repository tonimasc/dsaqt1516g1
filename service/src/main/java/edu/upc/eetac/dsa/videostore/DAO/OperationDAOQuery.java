package edu.upc.eetac.dsa.videostore.DAO;

public interface OperationDAOQuery {
    public final static String CREATE_COMPRA = "insert into compradas (usuarioid, peliculaid, numdescargas) values (UNHEX(?), UNHEX(?), ?)";
    public final static String UPDATE_COMPRA = "update compradas set numdescargas=? where usuarioid=unhex(?) and peliculaid=unhex(?)";
    public final static String DELETE_COMPRA = "delete from compradas where usuarioid=unhex(?) and peliculaid=unhex(?)";
    public final static String CREATE_RENT = "insert into alquiladas (usuarioid, peliculaid, horasrestantes) values (UNHEX(?), UNHEX(?), ?)";
    public final static String UPDATE_RENT = "update alquiladas set horasrestantes=? where usuarioid=unhex(?) and peliculaid=unhex(?)";
    public final static String DELETE_RENT = "delete from alquiladas where usuarioid=unhex(?) and peliculaid=unhex(?)";
    public final static String GET_COMPRA_BY_ID = "select hex(usuarioid) as usuarioid, hex(peliculaid) as peliculaid, numdescargas, fechacompra from compradas where usuarioid=unhex(?) order by fechacompra DESC";
    public final static String GET_RENT_BY_ID = "select hex(usuarioid) as usuarioid, hex(peliculaid) as peliculaid, horasrestantes, fechacompra from alquiladas where usuarioid=unhex(?) order by fechacompra DESC";
    public final static String GET_COMPRA_BY_BOTH_ID = "select hex(usuarioid) as usuarioid, hex(peliculaid) as peliculaid, numdescargas, fechacompra from compradas where usuarioid=unhex(?) and peliculaid=unhex(?)";
    public final static String GET_RENT_BY_BOTH_ID = "select hex(usuarioid) as usuarioid, hex(peliculaid) as peliculaid, numdescargas, horasrestantes from alquiladas where usuarioid=unhex(?) and peliculaid=unhex(?)";

}
