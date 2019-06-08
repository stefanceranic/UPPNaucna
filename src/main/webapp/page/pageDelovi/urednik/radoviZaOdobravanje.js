naucnaCentrala.controller('radoviZaOdobravanje', ['$rootScope','$scope','$state','$http', function ($rootScope, $scope,$state,$http) {


    $scope.taskovi = [];

    $scope.prikaziCasopise = function() {
        $http.get("http://localhost:8080/process/aproveTasks/"+$rootScope.korisnik.email).then(
            function(response) {
                $scope.taskovi = response.data;

            }, function(response) {
            });
    };

    $scope.odaberiCasopis = function(x) {
            	$state.go("urednikPregledRada", {"rad": x.rad, "task": x.taskId, "processInstanceId": x.processId});
    };
    $scope.prikaziCasopise();

}]);