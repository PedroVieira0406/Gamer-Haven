package br.unitins.topicos1.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    // sequencia aleatória a ser adicionada na senha
    private String salt = "#blahxyz17";
    // contagem de iteracoes
    private Integer iterationCount = 405;
    // comprimento do hash em bits
    private Integer keyLength = 512;

    @Override
    public String getHashSenha(String senha) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(
                            new PBEKeySpec(senha.toCharArray(), salt.getBytes(), iterationCount, keyLength))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HashService hash = new HashServiceImpl();
        System.out.println(hash.getHashSenha("123"));
        System.out.println(hash.getHashSenha("322"));
        System.out.println(hash.getHashSenha("Deus"));
        // 2jqHB2Uf9imuz2oRVlzQCEMTCOoHPgbnPCwXCm100JmUzMNhlZFMjcXoeWp9T91TTCruG2sL5JNYRvt6wtw2Ew==
        // j+Z+xmgZtP05avyaIysXHHnNH/N4LFtr6WCgYGgpMciYGzWUtilGg1bl7LQ2n4sadR/BQJEXjMrk1XRFg2jMSg==
        // Deus=  AblfspGdnCpnKpc5+AZ5EIfxn4QitFScugvcCx0ZKpl1BACS9gauXq8GpH3uIl7X0n8jkLXlvKUaXKeEmDm3NQ==
    }

}