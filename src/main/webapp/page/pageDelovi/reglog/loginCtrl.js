naucnaCentrala.controller('loginCtrl', ['$rootScope',
		'$scope',
		'$state',
		'$http',
		'FileSaver',
		'Blob',
		function($rootScope, $scope, $state, $http, FileSaver, Blob) {

			$rootScope.korisnik = {};
			$scope.properties = [];
			$scope.model = {};
			$scope.gde = {};
			$scope.korisnikDTO = {};
			$scope.toRegister = function() {
				$state.go("register");
			};

			$scope.submit = function() {
				$http.post("http://localhost:8080/user/postLogin",
						$scope.korisnikDTO).then(function(response) {
					$rootScope.korisnik = response.data;
					if (response.data.tipKorisnika == "autor") {
						$state.go("pocetnaAutor")
					} else if (response.data.tipKorisnika == "recenzent") {
						$state.go("pocetnaRecenzent");
					} else {
						$state.go("pocetnaUrednik");
					}
				}, function(response) {
					alert("Username ili password su pogresni")
				});

			}
		} ]);