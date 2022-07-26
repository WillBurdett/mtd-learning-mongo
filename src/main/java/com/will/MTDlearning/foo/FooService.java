package com.will.MTDlearning.foo;

import com.will.MTDlearning.exceptions.FooNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FooService {

    private final FooRepository fooRepository;

    @Autowired
    public FooService(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    public List<Foo> getAllFoo() {
        return fooRepository.findAll();
    }

    public Optional<Foo> getFooByName(String name) {
        Optional<Foo> fooWithName = fooRepository.findById(name);
        if (fooWithName.isPresent()){
           return fooRepository.findById(name);
       } else {
           throw new FooNotFound(name + " not found");
        }
    }

    public void addFoo(Foo foo) {
        fooRepository.insert(foo);
    }

    public void deleteFooByName(String name) {
        Optional<Foo> fooWithName = fooRepository.findById(name);
        if (fooWithName.isPresent()){
            fooRepository.deleteById(name);
        } else {
            throw new FooNotFound(name + " not found");
        }
    }

    public void updateFoo(String name, Foo foo) {
        Optional<Foo> fooWithName = fooRepository.findById(name);
        if (fooWithName.isPresent()){
            Foo updateFoo = fooWithName.get();
            updateFoo.setName(foo.getName());
            updateFoo.setLegs(foo.getLegs());
            updateFoo.setCanFly(foo.getCanFly());
            fooRepository.save(updateFoo);
        } else {
            throw new FooNotFound(name + " not found");
        }
    }
}
