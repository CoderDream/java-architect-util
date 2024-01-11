package com.coderdream.notebook.repository;

import com.coderdream.notebook.entity.Notebook;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotebookRepository extends CrudRepository<Notebook, Long> {

    List<Notebook> findByName(String name);

}
