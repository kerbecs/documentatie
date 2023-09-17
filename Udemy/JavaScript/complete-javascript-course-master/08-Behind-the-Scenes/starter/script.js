const airline1 = {
  name: 'Fly',
  clients: 500,
  getInfo(year) {
    console.log(`Company ${this.name} has ${this.clients} clients in ${year}`)
  }
}

const airline2 = {
  name: 'MDA',
  clients: 150,
}
const func = airline1.getInfo.bind(airline2);

func(2023)
