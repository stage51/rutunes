<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Баланс</h1>
    <hr>
    <a class="btn btn-primary mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Пополнить баланс
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/balance" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="typeBalance">Тип оплаты</label>
                <select id="typeBalance" required name="type" class="form-control">
                    <option value="Qiwi">Qiwi</option>
                    <option value="Paypal">Paypal</option>
                    <option value="Card">Банковская карта</option>
                </select>
            </div>
            <div class="form-group">
                <label for="userBalance">Сумма</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="userBalance" name="balance">
                    <div class="input-group-append">
                        <span class="input-group-text">рублей</span>
                    </div>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-primary">Пополнить</button>
        </form>
    </div>
</@t.template>