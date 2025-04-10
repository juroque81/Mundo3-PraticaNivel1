package model;
import java.io.*;
import java.util.*;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> lista = new ArrayList<>();

    public void inserir(PessoaJuridica pj) {
        lista.add(pj);
    }

    public void alterar(PessoaJuridica pj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == pj.getId()) {
                lista.set(i, pj);
                break;
            }
        }
    }

    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pj : lista) {
            if (pj.getId() == id) return pj;
        }
        return null;
    }

    public List<PessoaJuridica> obterTodos() {
        return lista;
    }

    public void persistir(String nomeArquivo) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(lista);
        }
    }

    public void recuperar(String nomeArquivo) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (List<PessoaJuridica>) in.readObject();
        }
    }
}