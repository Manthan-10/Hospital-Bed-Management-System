import React from "react";
import Enzyme, { shallow, mount } from "enzyme";
import Hospital from "../components/Hospital";
import Adapter from "enzyme-adapter-react-16";
import * as reactRouterDom from "react-router-dom";
import renderer from "react-test-renderer";
import { Provider } from "react-redux";
import configureStore from "redux-mock-store";
import { render } from "@testing-library/react";
import BedService from "../services/bed.service";

Enzyme.configure({ adapter: new Adapter() });

/**
 * Testing for UI of Hospital Component
 * @author MN097789
 */

const mockNavigate = jest.fn();

jest.mock("react-router-dom", () => ({
  useSelector: jest.fn(),

  useNavigate: () => mockNavigate,
}));
const mockNavigateSpy = jest.spyOn(reactRouterDom, "useNavigate");

const initialState = { output: 10 };
const mockStore = configureStore();
let store;

describe("Hospital UI Test Suite", () => {
  let wrapper;
  beforeEach(() => {
    wrapper = shallow(<Hospital />);
  });

  test("Checks if it renders correctly", () => {
    store = mockStore(initialState);
    const tree = renderer
      .create(
        <Provider store={store}>
          <Hospital />
        </Provider>
      )
      .toJSON();
    expect(tree).toMatchSnapshot();
  });

  test("should check for divs", () => {
    wrapper = shallow(<Hospital />);
    expect(wrapper.length).toEqual(1);
  });

  test("BedService.getHospitalById is called", () => {
    jest.spyOn(React, "useEffect").mockImplementation((f) => f());
    jest.spyOn(BedService, "getHospitalById");
    jest.spyOn(global.console, "log");
    render(
      <Provider store={store}>
        <Hospital />
      </Provider>
    );

    expect(BedService.getHospitalById).toHaveBeenCalled();
  });
});
