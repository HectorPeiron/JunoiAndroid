package com.tfgjunio.api;

import com.tfgjunio.domain.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JunioApiInterface {

    // Animal endpoints
    @GET("/animales")
    Call<List<Animal>> getAnimales();

    @POST("/animales")
    Call<Animal> addAnimal(@Body Animal animal);

    @PUT("/animales/{id}/{tipoAnimalId}/{crianzaId}")
    Call<Animal> modifyAnimal(@Path("id") long id, @Path("tipoAnimalId") long tipoAnimalId, @Path("crianzaId") long crianzaId, @Body Animal animal);

    @DELETE("/animales/{id}")
    Call<Void> deleteAnimal(@Path("id") long id);

    // Baja endpoints
    @GET("/bajas")
    Call<List<Baja>> getBajas();

    @POST("/bajas")
    Call<Baja> addBaja(@Body Baja baja);

    @PUT("/bajas/{id}/{tipoBajaId}/{crianzaId}")
    Call<Baja> modifyBaja(@Path("id") long id, @Path("tipoBajaId") long tipoBajaId, @Path("crianzaId") long crianzaId, @Body Baja baja);

    @DELETE("/bajas/{id}")
    Call<Void> deleteBaja(@Path("id") long id);

    // Compra endpoints
    @GET("/compras")
    Call<List<Compra>> getCompras();

    @POST("/compras")
    Call<Compra> addCompra(@Body Compra compra);

    @DELETE("/compras/{id}")
    Call<Void> deleteCompra(@Path("id") long id);

    // Crianza endpoints
    @GET("/crianzas")
    Call<List<Crianza>> getCrianzas();

    @POST("/crianzas")
    Call<Crianza> addCrianza(@Body Crianza crianza);

    @PUT("/crianzas/{id}")
    Call<Crianza> modifyCrianza(@Path("id") long id, @Body Crianza crianza);

    @DELETE("/crianzas/{id}")
    Call<Void> deleteCrianza(@Path("id") long id);

    // Recurso endpoints
    @GET("/recursos")
    Call<List<Recurso>> getRecursos();

    @GET("/recursos/{id}")
    Call<Recurso> getRecurso(@Path("id") long id);

    // TipoAnimal endpoints
    @GET("/tipoAnimales")
    Call<List<TipoAnimal>> getTipoAnimales();

    @POST("/tipoAnimales")
    Call<TipoAnimal> addTipoAnimal(@Body TipoAnimal tipoAnimal);

    @GET("/tipoAnimales/{id}")
    Call<TipoAnimal> getTipoAnimal(@Path("id") long id);

    // TipoBaja endpoints
    @GET("/tipoBajas")
    Call<List<TipoBaja>> getTipoBajas();

    @POST("/tipoBajas")
    Call<TipoBaja> addTipoBaja(@Body TipoBaja tipoBaja);

    @GET("/tipoBajas/{id}")
    Call<TipoBaja> getTipoBaja(@Path("id") long id);

    // TipoRecurso endpoints
    @GET("/tipoRecursos")
    Call<List<TipoRecurso>> getTipoRecursos();

    @POST("/tipoRecursos")
    Call<TipoRecurso> addTipoRecurso(@Body TipoRecurso tipoRecurso);

    @GET("/tipoRecursos/{id}")
    Call<TipoRecurso> getTipoRecurso(@Path("id") long id);

    @PUT("/tipoRecursos/{id}")
    Call<TipoRecurso> modifyTipoRecurso(@Path("id") long id, @Body TipoRecurso tipoRecurso);

    @DELETE("/tipoRecursos/{id}")
    Call<Void> deleteTipoRecurso(@Path("id") long id);

    // Unidad endpoints
    @GET("/unidades")
    Call<List<Unidad>> getUnidades();

    @POST("/unidades")
    Call<Unidad> addUnidad(@Body Unidad unidad);

    @GET("/unidades/{id}")
    Call<Unidad> getUnidad(@Path("id") long id);

    @PUT("/unidades/{id}")
    Call<Unidad> modifyUnidad(@Path("id") long id, @Body Unidad unidad);

    @DELETE("/unidades/{id}")
    Call<Void> deleteUnidad(@Path("id") long id);
}
