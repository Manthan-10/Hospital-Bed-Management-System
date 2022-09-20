import React from "react";
import Enzyme, { shallow, mount } from "enzyme";
import Room from "../components/Room";
import Adapter from "enzyme-adapter-react-16";
import * as reactRouterDom from "react-router-dom";
import renderer from "react-test-renderer";
import { render } from "@testing-library/react";
import BedService from "../services/bed.service";

Enzyme.configure({ adapter: new Adapter() });

/**
 * Testing for UI of Login page
 * @author MN097789
 */

const mockNavigate = jest.fn();

jest.mock("react-router-dom", () => ({
  useSelector: jest.fn(),

  useNavigate: () => mockNavigate,
}));
const mockNavigateSpy = jest.spyOn(reactRouterDom, "useNavigate");

describe("Room UI Test Suite", () => {
  let wrapper;
  beforeEach(() => {
    wrapper = shallow(<Room />);
  });

  test("Checks if it renders correctly", () => {
    const tree = renderer.create(<Room />).toJSON();
    expect(tree).toMatchSnapshot();
  });

  test("should check for divs", () => {
    wrapper = shallow(<Room />);
    expect(wrapper.length).toEqual(1);
  });
});
