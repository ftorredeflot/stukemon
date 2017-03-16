/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author ferran
 */
@Stateless
public class StukemonEjb {

    @PersistenceUnit
    EntityManagerFactory emf;

    public boolean insertTrainer(Trainer t) {
        if (!existTrainer(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.flush();  //Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }
 public boolean insertarPokemon(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        Pokemon encontrado = em.find(Pokemon.class, p.getName());
        
        if (encontrado == null) {
            em.persist(p);
            em.flush();   //Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }
    public boolean existTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer encontrado = em.find(Trainer.class, t.getName());
        em.close();
        return encontrado != null;
    }
    
    
       public List<Trainer> getTrainersForAddPokemons(){
        EntityManager em = emf.createEntityManager();
        
        /*Query q = em.createQuery("SELECT trainer FROM Trainer trainer WHERE trainer.pokemonCollection < 6");
        List<Trainer> trainers = q.getResultList();*/
        List<Trainer> trainers = em.createNamedQuery("Trainer.findAll").getResultList();
        trainers.stream().filter((t) -> (t.getPokemonCollection().size() >= 6)).forEachOrdered((t) -> {
            trainers.remove(t);
        });
        em.close();
        return trainers;
    }
           public Trainer findTrainerByName(String name){
        EntityManager em = emf.createEntityManager();
        Trainer encontrado = em.find(Trainer.class, name);
        em.close();
        
        return encontrado;
    }
           
        public List<Pokemon> GetAllPokemons(){
        
        List<Pokemon> pokemons =emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
        
        return pokemons;
        }
        
        public boolean RmPokemon(String name){
        EntityManager em = emf.createEntityManager();
        Pokemon p = em.find(Pokemon.class, name);
        if(p != null){
            em.remove(p);
            return true;
        }
        return false;
    }

}
