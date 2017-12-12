<!DOCTYPE html>

<html lang="en" ng-app="testeApp">
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>
    <meta charset="UTF-8">
    <title>Teste</title>
  </head>
  <body>
    <div ng-controller="IndexController" ng-init="init()">
      <div class="jumbotron">
          <div class="container">
            <h1>Portfólio de projetos</h1>
          </div>
      </div>

      <div ng-if="!editing">
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">Nome</th>
              <th scope="col">Descrição</th>
              <th scope="col">Data de início</th>
              <th scope="col">Gerente responsável</th>
              <th scope="col">Previsão de término</th>
              <th scope="col">Data real de término</th>
              <th scope="col">Orçamento total</th>
              <th scope="col">Status</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr ng-repeat="project in projects">
              <td>{{project.name}}</td>
              <td>{{project.description}}</td>
              <td>{{project.endDate | date: 'yyyy-MM-dd'}}</td>
              <td>{{project.projectDescription}}</td>
              <td>{{project.startDate | date: 'yyyy-MM-dd'}}</td>
              <td>{{project.termForecast | date: 'yyyy-MM-dd'}}</td>
              <td>{{project.totalBudget}}</td>
              <td>{{project.status.statusId}}</td>
              <td>
                <div class="btn-group pull-right" role="group" aria-label="...">
                  <button type="button" class="btn btn-default" ng-click="edit(project)">Edita</button>
                  <button ng-if="verify(project.status)" type="button" class="btn btn-danger" 
                    ng-click="delete(project.projectId, $index)">Remove</button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        <div class="btn-group pull-right" role="group" aria-label="..." style="padding-right:10px;">
          <button type="button" class="btn btn-primary" ng-click="newRegister()">Novo</button>
        </div>
      </div>

      <div ng-if="editing">
        <form style="margin: 10px;" name="form">
          <div class="form-group">
            <label for="inputNome">Nome</label>
            <input class="form-control" placeholder="Nome"
              ng-model="project.name"
              required>
          </div>
          <div class="form-group">
            <label for="inputSenha">Data de incio</label>
            <input type="" class="form-control" placeholder=""
              ng-model="project.startDate"
              required>
          </div>
          <div class="form-group">
            <label for="inputSenha">Gerente responsável</label>
            <input type="" class="form-control" placeholder=""
              ng-model="project.description"
              required>
          </div>
          <div class="form-group">
            <label for="inputSenha">Previsão de término</label>
            <input type="" class="form-control" placeholder=""
              ng-model="project.termForecast"
              required>
          </div>
          <div class="form-group">
            <label for="inputSenha">Data de término</label>
            <input type="" class="form-control" placeholder=""
              ng-model="project.endDate"
              required>
          </div>
          <div class="form-group">
            <label for="inputSenha">Orçamento total</label>
            <input type="" class="form-control" placeholder=""
              ng-model="project.totalBudget"
              required>
          </div>

            <label for="inputSenha">Status</label>
          <select class=""
              ng-model="project.status.id"
              ng-options='allStatus'
              required>
          <div class="form-group">
            <label for="inputSenha">Descrição</label>
            <input type="" class="form-control" placeholder=""
              ng-model="project.projectDescription"
              required>
          </div>
          <button type="submit" class="btn btn-success" 
            ng-click="save(project)"
            ng-disabled="form.$invalid">Salvar</button>
          <button type="submit" class="btn btn-primary" ng-click="listing()">Cancelar</button>
        </form>
      </div>
    </div>

    <script type="text/javascript">
      var testeApp = angular.module("testeApp", []);
      testeApp.controller("IndexController", function($scope, $http) {
        
        $scope.projects = [];
        $scope.editing = false;
        $scope.project = {};

        $scope.verify = function(status) {
          if(status.id == 4 || status.id == 6 || status.id == 7) return false;
          return true;
        }

        $scope.allStatus = function() {
          $http.get('/status').then(function(res) {
            $scope.allStatus = res.data;
            }, function(err) {
             window.alert('Não foi possível recuperar os registros');
          });
        }

        $scope.init = function() {
          $scope.list();
          $scope.allStatus();
          $scope.doEditing(false);
        }

        $scope.list = function() {
          $http.get('/project').then(function(res) {
            $scope.projects = res.data;
          }, function(err) {
            window.alert('Não foi possível recuperar os registros');
          });
        }

        $scope.save = function(project) {
          project.endDate = new Date(project.endDate).getTime();
          project.startDate = new Date(project.startDate).getTime();
          project.termForecast = new Date(project.termForecast).getTime();

          $http.post('/project', project).then(function(res) {
            $scope.projects.push(res.data);
            $scope.listing();
          }, function(err) {
            window.alert("Não foi possível salvar o registro");
          });
        }

        $scope.delete = function(id, index) {
          $http.delete('/project/' + id).then(function(res) {
            $scope.projects.splice(index, 1);
          }, function(err) {
            window.alert("Não foi possível remover o registro");
          });
        }

        $scope.doEditing = function(flag) {
          $scope.editing = flag;
        }

        $scope.edit = function(project) {
          $scope.doEditing(true);
          $scope.project = project;
          $scope.project.endDate = new Date(project.endDate).toLocaleDateString();
          $scope.project.startDate = new Date(project.startDate).toLocaleDateString();
          $scope.project.termForecast = new Date(project.termForecast).toLocaleDateString();
        }

        $scope.listing = function() {
          $scope.list();
          $scope.doEditing(false);
        }

        $scope.newRegister = function() {
          $scope.doEditing(true);
          $scope.project = {};
        }
      }); 
    </script>
  </body>
</html>

