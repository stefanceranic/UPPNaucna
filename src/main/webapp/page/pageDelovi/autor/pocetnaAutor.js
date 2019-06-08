naucnaCentrala.controller('pocetnaAutor', [ '$rootScope', '$scope', '$state',
		'$http', function($rootScope, $scope, $state, $http) {

			$scope.toPrijaviRad = function() {
				$state.go("odabirCasopisa");
			};

			$scope.toRadoviZaIspravku = function() {
				$state.go("odabirRadaZaIspravku");
			};

			$scope.logout = function() {
				$rootScope.korisnik = "";
				$state.go("login");
			}
		} ]);