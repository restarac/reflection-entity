/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reflection.entity;

import br.com.entity.test.Embalagem;
import br.com.entity.test.GrupoProduto;
import br.com.entity.test.GrupoProdutoPK;
import br.com.entity.test.Produto;
import br.com.entity.test.ProdutoPK;
import br.com.entity.test.SubGrupoProduto;
import br.com.entity.test.SubGrupoProdutoPK;
import br.com.entity.test.TipoEmbalagem;
import br.com.entity.test.TipoProduto;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederico
 */
public class ReflectionEntityTest {

    public static final String COLUNAS_SIMPLES = "gg_pais[codigo_pais=999 nome_pais=Brasil nacionalidade_pais=Brasileiro]";
    public static final String COLUNAS_SIMPLES_NULL = "gg_pais[codigo_pais=999 nome_pais=Brasil nacionalidade_pais=null]";
    public static final String COLUNAS_JOIN = "gg_estado[codigo_estad=1111 sigla_estad=RJ nome_estad=Rio de Janeiro codigo_estado_correio=222222 codigo_pais=999 codigo_ibge=33333333]";
    public static final String COLUNAS_JOIN_NULL = "gg_estado[codigo_estad=1111 sigla_estad=RJ nome_estad=Rio de Janeiro codigo_estado_correio=null codigo_pais=null codigo_ibge=null]";
    public static final String COLUNAS_JOINS = "gg_produtos[codigo_emp=001 codigo_prod=123123  nome_prod=Captopril codigo_barra=123123123123123123 codigo_auxiliar=null codigo_prod_estado=null ativo_inativo=A mantido_estoque=null tributado=null descricao_prod=Medicamento para dores codigo_emp=001 codigo_embalag=321321 codigo_embalag=null codigo_emp=001 codigo_emp=001 codigo_gp=111111 codigo_gp=null codigo_emp=001 codigo_sg=1111111 codigo_sg=null codigo_sis=9A codigo_emp=null codigo_tpp=null codigo_tpp=null]";
    public static final String COLUNAS_JOINS_NULL = "gg_produtos[codigo_emp=001 codigo_prod=123123  nome_prod=Captopril codigo_barra=null codigo_auxiliar=null codigo_prod_estado=null ativo_inativo=A mantido_estoque=null tributado=null descricao_prod=Medicamento para dores codigo_emp=null codigo_embalag=null codigo_embalag=null codigo_emp=001 codigo_emp=null codigo_gp=null codigo_gp=null codigo_emp=001 codigo_sg=1111111 codigo_sg=null codigo_sis=null codigo_emp=null codigo_tpp=null codigo_tpp=null]";
    public static final String COLUNAS_NULL = "gg_usuario[codigo_sis=9A codigo_user=00001  senha=null status_user=null data_expira=null master_user=null user_name=null serial_certificado=null codigo_emp=null codigo_emp=null codigo_g=null codigo_g=null codigo_grp_usr=null codigo_sis=null codigo_grp_usr=null codigo_sis=null]";

    public ReflectionEntityTest() {
    }

//    @Test
//    public void testEntityToString_PROXY() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reflectionPUPostgres");
//        EntityManager em = emf.createEntityManager();
//        Pais reference = em.getReference(Pais.class, "001");
//        reference.getCodigo();
//        String entityToString = ReflectionEntity.entityToString(reference);
//        System.out.println("ToString PROXY: " + entityToString);
//        assertEquals("gg_pais[codigo_pais=001 nome_pais=Brasil nacionalidade_pais=Brasileiro]", entityToString);
//    }
//
//    @Test
//    public void testEntityToString_PROXY2() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reflectionPUPostgres");
//        EntityManager em = emf.createEntityManager();
//        List<Pais> list = em.createNamedQuery("Pais.findByNome").setParameter(1, "%A%").getResultList();
//        Pais reference = list.get(0);
//        reference.getCodigo();
//        String entityToString = ReflectionEntity.entityToString(reference);
//        System.out.println("ToString PROXY: " + entityToString);
//        assertEquals("gg_pais[codigo_pais=001 nome_pais=Brasil nacionalidade_pais=Brasileiro]", entityToString);
//    }
    @Test
    public void testEntityToString_colunasSimples() {
        System.out.println("Teste Somente Colunas Simples");
        TipoProduto tipoProduto = new TipoProduto("000001", "Produto Limpeza", "Materiais de limpeza.");
        //
        String result = ReflectionEntity.entityToString(tipoProduto);
        System.out.println("result ColunasSimples ... " + result);
        assertEquals("tipo_produto[codigo_tipo_produto='000001' nome_tipo_produto='Produto Limpeza' descricao_tipo_produto='Materiais de limpeza.']", result);
    }

    @Test
    public void testEntityToString_someColunasNull() {
        System.out.println("Teste algumas Colunas null");
        TipoProduto tipoProduto = new TipoProduto("000001", "Produto Limpeza", null);
        //
        String result = ReflectionEntity.entityToString(tipoProduto);
        System.out.println("result SomeColunasSimplesNull... " + result);
        assertEquals("tipo_produto[codigo_tipo_produto='000001' nome_tipo_produto='Produto Limpeza' descricao_tipo_produto='null']", result);
    }

    @Test
    public void testEntityToString_colunasSimplesNull() {
        System.out.println("Teste Somente Colunas Simples Null");
        TipoProduto tipoProduto = new TipoProduto("000001", null, null);
        //
        String result = ReflectionEntity.entityToString(tipoProduto);
        System.out.println("result ColunasSimplesNull ... " + result);
        assertEquals("tipo_produto[codigo_tipo_produto='000001' nome_tipo_produto='null' descricao_tipo_produto='null']", result);
    }

    @Test
    public void testEntityToString_colunasJoin() {
        System.out.println("Teste Somente Colunas JOIN");
        Embalagem embalagem = new Embalagem("0000012", "10 Litros", "Garrafao para bebedouro de 10L", "GARR 10L");
        TipoEmbalagem tipoEmbalagem = new TipoEmbalagem(12, "Garrafas", "Embalagem de Garrafas");
        embalagem.setEmbalagem(tipoEmbalagem);
        //
        String result = ReflectionEntity.entityToString(embalagem);
        System.out.println("result colunasJoin ... " + result);
        assertEquals("embalagem[codigo_embalagem='0000012' nome_embalagem='10 Litros' desc_embalagem='Garrafao para bebedouro de 10L' abreviatura_embalagem='GARR 10L' codigo_tipo_embalagem='12']", result);
    }

    @Test
    public void testEntityToString_colunasJoinNull() {
        System.out.println("Teste Somente Colunas JOIN Null");
        Embalagem embalagem = new Embalagem("0000012", "10 Litros", "Garrafao para bebedouro de 10L", "GARR 10L");
        embalagem.setEmbalagem(null);
        //
        String result = ReflectionEntity.entityToString(embalagem);
        System.out.println("result colunasJoin ... " + result);
        assertEquals("embalagem[codigo_embalagem='0000012' nome_embalagem='10 Litros' desc_embalagem='Garrafao para bebedouro de 10L' abreviatura_embalagem='GARR 10L' codigo_tipo_embalagem=null]", result);
    }

    @Test
    public void testEntityToString_colunasJoins() {
        System.out.println("Teste Somente Colunas JOINS");
        //
        Embalagem embalagem = new Embalagem("0000122", "Gramas", "Alguma em embalagem em Gramas...","g");
        //
        GrupoProduto grupoProduto = new GrupoProduto(new GrupoProdutoPK("001", "001"), "Material", "Materias em geral");
        //
        SubGrupoProduto subGrupoProduto = new SubGrupoProduto(new SubGrupoProdutoPK("0001", "001"), "Limpeza", "Limpeza de ambientes e geral.");
        //
        TipoProduto tipoProduto = new TipoProduto("000011", "Produto Corrosivo", "Limpeza em Geral ou outros...");
        //
        Produto produto = new Produto(new ProdutoPK("123123", "001"));
        produto.setAtivoInativo('A');
        produto.setGrupoProduto(grupoProduto);
        produto.setSubGrupoProduto(subGrupoProduto);
        produto.setTipoProduto(tipoProduto);
        produto.setEmbalagem(embalagem);
        //
        String result = ReflectionEntity.entityToString(produto);
        System.out.println("result colunasJoin ... " + result);
        assertEquals("produto[codigo_emp='001' codigo_produto='123123'  nome_prod='null' codigo_barra='null' ativo_inativo='A' descricao_prod='null' codigo_embalagem='0000122' codigo_emp=001 codigo_gp= codigo_emp=001 codigo_sub_grupo=0001 codigo_emp=Vazio... codigo_tpp=Vazio...]", result);
    }

    @Test
    public void testEntityToString_colunasJoinsNull() {
        System.out.println("Teste Somente Colunas JOINS Null");
        //
        Embalagem embalagem = new Embalagem("0000122", "Gramas", "Alguma em embalagem em Gramas...","g");
        //
        GrupoProduto grupoProduto = new GrupoProduto(new GrupoProdutoPK("001", "001"), "Material", "Materias em geral");
        //
        Produto produto = new Produto(new ProdutoPK("123123", "001"));
        produto.setAtivoInativo('A');
        produto.setGrupoProduto(grupoProduto);
        produto.setEmbalagem(embalagem);
        //
        String result = ReflectionEntity.entityToString(produto);
        System.out.println("result colunasJoin ... " + result);
        assertEquals("produto[codigo_emp='001' codigo_produto='123123'  nome_prod='null' codigo_barra='null' ativo_inativo='A' descricao_prod='null' codigo_embalagem='0000122' codigo_emp=001 codigo_gp= codigo_emp=null codigo_sub_grupo=null codigo_emp=null codigo_tpp=null]", result);
    }
}
