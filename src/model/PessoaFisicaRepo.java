package model;
import java.io.*;
import java.util.*;

public class PessoaFisicaRepo {
    private List<PessoaFisica> lista = new ArrayList<>();

    public void inserir(PessoaFisica pf) {
        lista.add(pf);
    }

    public void alterar(PessoaFisica pf) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == pf.getId()) {
                lista.set(i, pf);
                break;
            }
        }
    }

    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica pf : lista) {
            if (pf.getId() == id) return pf;
        }
        return null;
    }

    public List<PessoaFisica> obterTodos() {
        return lista;
    }

    public void persistir(String nomeArquivo) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(lista);
        }
    }

    public void recuperar(String nomeArquivo) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (List<PessoaFisica>) in.readObject();
        }
    }
}