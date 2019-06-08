naucnaCentrala.controller('registerCtrl', ['$scope','$state','$http', function ($scope,$state,$http) {

    $scope.properties=[];
    $scope.model={};

    $scope.pokreniProccess = function() {
        $http.get("http://localhost:8080/user/getRegister")
            .then(function(response) {
                $scope.properties=response.data;

            }, function(response) {
            });
    };
    $scope.submit=function(){
        var o = [];
        for (var m in $scope.model) {
            console.log(m + "  -  " + $scope.model[m]);
            o.push({ "fieldId": m, "fieldValue": $scope.model[m] });

        }
        console.log(o);
        $http.post("http://localhost:8080/user/post/"+$scope.properties.taskId, o)
            .then(function (response) {
                    alert("Uspesno ste registrovani")
                    $state.go("login");
            }
            , function (response) {
                    alert("Postoji korisnik sa ovim user-om")
            });

    }
    $scope.pokreniProccess();
}]);