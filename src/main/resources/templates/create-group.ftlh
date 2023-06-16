<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Создать группу</h1>
    <hr>
    <div class="container">
    <form action="/create/group" method="post">
        <div class="form-row align-items-center mb-4">
            <div class="col">
                <input type="text" class="form-control" required id="name" name="name" placeholder="Название">
            </div>
            <div class="col">
                <input type="date" class="form-control" required id="dateOfCreating" name="dateOfCreating" placeholder="Дата создания">
            </div>
            <div class="col-auto">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Изменить</button>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
    </div>
</@t.template>