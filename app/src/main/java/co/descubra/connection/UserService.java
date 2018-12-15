package co.descubra.connection;

import android.content.Context;
import android.widget.Toast;

import co.descubra.model.User;
import co.descubra.util.ResourcesUtil;
import co.descubra.util.ServiceGeneratorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserService {

    public void sendUser(User user, final Context ctx){

        Retrofit retrofit = ServiceGeneratorUtil.retrofitBuilder(ResourcesUtil.URL_BASE);
        DescubraAPI descubraAPI = retrofit.create(DescubraAPI.class);
        Call<User> request = descubraAPI.addUser(user);
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    System.out.println("Cadastrou");
                    if(response.body() != null){
                        Toast.makeText(ctx.getApplicationContext(), "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(ctx.getApplicationContext(), "Já existe um usuário com esse email ou houve um erro no sistema, desculpa!: " + response.code() + " Response: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                /*Toast.makeText(ctx.getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();*/
            }
        });
    }

}
