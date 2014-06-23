/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.Episodios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface EpisodiosFacadeLocal {

    void create(Episodios episodios);

    void edit(Episodios episodios);

    void remove(Episodios episodios);

    Episodios find(Object id);

    List<Episodios> findAll();

    List<Episodios> findRange(int[] range);

    int count();
    
}
